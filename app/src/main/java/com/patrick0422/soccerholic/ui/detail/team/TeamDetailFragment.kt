package com.patrick0422.soccerholic.ui.detail.team

import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.patrick0422.soccerholic.R
import com.patrick0422.soccerholic.base.BaseFragment
import com.patrick0422.soccerholic.data.local.TeamBookmarkEntity
import com.patrick0422.soccerholic.data.remote.response.TeamData
import com.patrick0422.soccerholic.databinding.FragmentTeamDetailBinding
import com.patrick0422.soccerholic.ui.MainViewModel
import com.patrick0422.soccerholic.ui.search.SearchViewModel
import com.patrick0422.soccerholic.util.Constants.TAG
import com.patrick0422.soccerholic.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailFragment : BaseFragment<FragmentTeamDetailBinding>(R.layout.fragment_team_detail) {
    private val args by navArgs<TeamDetailFragmentArgs>()
    private val mainViewModel: MainViewModel by activityViewModels()
    private val searchViewModel: SearchViewModel by activityViewModels()
    private val squadAdapter = SquadAdapter()

    private var isBookmarked = false
    private var currentTeamBookmarkEntity: TeamBookmarkEntity? = null

    override fun init() {
        binding.squadRecyclerView.adapter = squadAdapter

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        getTeamData()
    }

    private fun getTeamData() {
        searchViewModel.searchTeamWithTeamId(args.teamId)
        searchViewModel.idSearchData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    setData(result.data!!.response[0])
                    isLoading(false)
                }
                is NetworkResult.Error -> {
                    Log.d(TAG, "getTeamData: ${result.message}")
                    isLoading(false)
                }
                is NetworkResult.Loading -> {
                    isLoading(true)
                }
            }
        }
        getSquadData()
    }

    private fun getSquadData() {

    }

    private fun checkBookmarkStatus(item: MenuItem) {
        mainViewModel.readTeamBookmark.observe(this) { result ->

            val idList = result.filter {
                it.favoriteTeam.team.id == args.teamId
            }

            if (idList.isNotEmpty()) {
                isBookmarked = true
                currentTeamBookmarkEntity = idList[0]
            }

            applyBookmarkStatus(item)
        }
    }


    private fun setData(teamData: TeamData) = with(binding) {
        Glide
            .with(binding.root)
            .load(teamData.team.logo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageLogo)

        with(teamData) {
            textName.text = team.name
            textVenue.text =
                Html.fromHtml(getString(R.string.venue, venue.name), Html.FROM_HTML_MODE_LEGACY)
            textCountry.text =
                Html.fromHtml(getString(R.string.country, team.country), Html.FROM_HTML_MODE_LEGACY)
            textFounded.text =
                Html.fromHtml(getString(R.string.founded, team.founded), Html.FROM_HTML_MODE_LEGACY)
        }
    }

    private fun isLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_team_detail, menu)

        val item = menu.findItem(R.id.toolbar_bookmark)

        checkBookmarkStatus(item)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.toolbar_bookmark) {
            onBookmarkPressed(item)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun onBookmarkPressed(item: MenuItem) {
        isBookmarked =
            if (isBookmarked) {
                currentTeamBookmarkEntity?.let { mainViewModel.deleteTeamBookmark(it) }
                false
            } else {
                searchViewModel.idSearchData.value?.data?.response?.let {
                    mainViewModel.insertTeamBookmark(TeamBookmarkEntity(it[0]))
                }
                true
            }

        mainViewModel.readTeamBookmark
        applyBookmarkStatus(item)
        makeToast(isBookmarked.toString())
    }

    private fun applyBookmarkStatus(item: MenuItem) {
        if (isBookmarked)
            item.icon.setTint(resources.getColor(R.color.teal_200, null))
        else
            item.icon.setTint(resources.getColor(R.color.mediumGray, null))
    }
}