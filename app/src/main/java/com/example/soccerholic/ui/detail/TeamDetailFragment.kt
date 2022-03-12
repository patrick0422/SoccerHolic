package com.example.soccerholic.ui.detail

import android.os.Build
import android.text.Html
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.soccerholic.R
import com.example.soccerholic.base.BaseFragment
import com.example.soccerholic.data.remote.model.TeamData
import com.example.soccerholic.databinding.FragmentTeamDetailBinding
import com.example.soccerholic.ui.search.SearchViewModel
import com.example.soccerholic.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailFragment : BaseFragment<FragmentTeamDetailBinding>(R.layout.fragment_team_detail) {
    private val args by navArgs<TeamDetailFragmentArgs>()
    private val searchViewModel: SearchViewModel by viewModels()
    private val squadAdapter = SquadAdapter()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun init() {
        binding.squadRecyclerView.adapter = squadAdapter

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        searchViewModel.searchTeamWithTeamId(args.teamId)
        searchViewModel.idSearchData.observe(this) { result ->
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

        searchViewModel.searchSquadWithTeamId(args.teamId)
        searchViewModel.squadSearchData.observe(this) { result ->
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().navigateUp()
        }

        return super.onOptionsItemSelected(item)
    }
}