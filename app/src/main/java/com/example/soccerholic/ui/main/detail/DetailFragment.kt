package com.example.soccerholic.ui.main.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.soccerholic.R
import com.example.soccerholic.base.BaseFragment
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
                    with (binding) {
                        result.data!!.response[0].let { response ->
                            Glide
                                .with(binding.root)
                                .load(response.team.logo)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(imageLogo)
                            textName.text = response.team.name
                            textVenue.text = response.venue.name
                            text2.text = response.team.country
                            text3.text = "${response.team.founded}"
                        }
                    }
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }
        }
    }
}