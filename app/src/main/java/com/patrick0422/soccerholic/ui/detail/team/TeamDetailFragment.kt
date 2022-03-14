package com.patrick0422.soccerholic.ui.detail.team

import android.os.Build
import android.text.Html
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.patrick0422.soccerholic.R
import com.patrick0422.soccerholic.base.BaseFragment
import com.patrick0422.soccerholic.data.remote.model.TeamData
import com.patrick0422.soccerholic.databinding.FragmentTeamDetailBinding
import com.patrick0422.soccerholic.ui.search.SearchViewModel
import com.patrick0422.soccerholic.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailFragment : BaseFragment<FragmentTeamDetailBinding>(R.layout.fragment_team_detail) {
    private val args by navArgs<TeamDetailFragmentArgs>()
    private val searchViewModel: SearchViewModel by activityViewModels()
    private val squadAdapter = SquadAdapter()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun init() {
        binding.squadRecyclerView.adapter = squadAdapter

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if(searchViewModel.idSearchData.value == null || searchViewModel.idSearchData.value!!.data!!.response[0].team.id != args.teamId)
            searchViewModel.searchTeamWithTeamId(args.teamId)
        searchViewModel.idSearchData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    setData(result.data!!.response[0])
                    isLoading(false)
                }
                is NetworkResult.Error -> {
                    makeToast("Error: ${result.message}")
                    isLoading(false)
                }
                is NetworkResult.Loading -> {
                    isLoading(true)
                }
            }
        }

        if(searchViewModel.squadSearchData.value == null || searchViewModel.squadSearchData.value!!.data!!.response[0].team.id != args.teamId)
            searchViewModel.searchSquadWithTeamId(args.teamId)
        searchViewModel.squadSearchData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    squadAdapter.setData(result.data!!.response[0].players)
                    isSquadInfoEmpty(false)
                }
                is NetworkResult.Error -> {
                    isSquadInfoEmpty(true)
                }
                is NetworkResult.Loading -> {

                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setData(teamData: TeamData) = with(binding) {
        Glide
            .with(binding.root)
            .load(teamData.team.logo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageLogo)

        with(teamData) {
            textName.text = team.name
            textVenue.text = Html.fromHtml(getString(R.string.venue, venue.name), Html.FROM_HTML_MODE_LEGACY)
            textCountry.text = Html.fromHtml(getString(R.string.country, team.country), Html.FROM_HTML_MODE_LEGACY)
            textFounded.text =  Html.fromHtml(getString(R.string.founded, team.founded), Html.FROM_HTML_MODE_LEGACY)
        }
    }

    private fun isLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun isSquadInfoEmpty(isEmpty: Boolean) = with(binding) {
        if (isEmpty) {
            imageError.visibility = View.VISIBLE
            textError.visibility = View.VISIBLE
            squadRecyclerView.visibility = View.INVISIBLE
        } else {
            imageError.visibility = View.GONE
            textError.visibility = View.GONE
            squadRecyclerView.visibility = View.VISIBLE
        }
    }
}