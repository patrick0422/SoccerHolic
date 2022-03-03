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
                        Glide
                            .with(binding.root)
                            .load(result.data!!.response[0].team.logo)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(imageLogo)
                        textName.text = result.data.response[0].team.name
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