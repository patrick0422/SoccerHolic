package com.example.soccerholic.ui.main.detail

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.soccerholic.R
import com.example.soccerholic.base.BaseFragment
import com.example.soccerholic.data.search.Response
import com.example.soccerholic.databinding.FragmentDetailBinding
import com.example.soccerholic.ui.main.search.SearchViewModel
import com.example.soccerholic.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val args by navArgs<DetailFragmentArgs>()
    private val searchViewModel: SearchViewModel by viewModels()

    override fun init() {
        searchViewModel.searchTeamWithTeamId(args.teamId)

        searchViewModel.idSearchResponse.observe(this) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    setData(result.data!!.response[0])
                }
                is NetworkResult.Error -> {
                    makeToast("Error: ${result.message}")
                }
                is NetworkResult.Loading -> {
                    isLoading(true)
                }
            }
        }
    }

    private fun setData(response: Response) = with(binding) {
        Glide
            .with(binding.root)
            .load(response.team.logo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageLogo)

        with(response) {
            textName.text = team.name
            textVenue.text = getString(R.string.venue, venue.name)
            textCountry.text = getString(R.string.country, team.country)
            textFounded.text = getString(R.string.founded, team.founded)
        }
    }

    private fun isLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}