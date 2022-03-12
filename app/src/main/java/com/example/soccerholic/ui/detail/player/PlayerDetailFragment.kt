package com.example.soccerholic.ui.detail.player

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.soccerholic.R
import com.example.soccerholic.base.BaseFragment
import com.example.soccerholic.data.remote.model.DetailedPlayerData
import com.example.soccerholic.databinding.FragmentPlayerDetailBinding
import com.example.soccerholic.ui.search.SearchViewModel
import com.example.soccerholic.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerDetailFragment : BaseFragment<FragmentPlayerDetailBinding>(R.layout.fragment_player_detail) {
    private val args by navArgs<PlayerDetailFragmentArgs>()
    private val searchViewModel: SearchViewModel by activityViewModels()
    private val statisticsListAdapter = StatisticsListAdapter()

    override fun init() = with(binding) {
        statisticsList.adapter = statisticsListAdapter

        if (searchViewModel.playerSearchData.value == null || searchViewModel.playerSearchData.value!!.data!!.response[0].playerDetailed.id != args.playerId)
            searchViewModel.searchPlayerWithPlayerId(args.playerId)
        searchViewModel.playerSearchData.observe(viewLifecycleOwner) { response ->
            when(response) {
                is NetworkResult.Success -> {
                    setData(response.data!!.response[0])
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }
        }
    }

    private fun setData(playerData: DetailedPlayerData) = with(binding) {
        Glide.with(binding.root)
            .load(playerData.playerDetailed.photo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .transform(CenterCrop(), RoundedCorners(50))
            .into(imagePlayerProfile)

        textPlayerName.text = playerData.playerDetailed.name
        textPlayerAge.text = requireActivity().getString(R.string.age, playerData.playerDetailed.age)
        textPlayerHeight.text = requireActivity().getString(R.string.height, playerData.playerDetailed.height)
        textPlayerWeight.text = requireActivity().getString(R.string.weight, playerData.playerDetailed.weight)

        statisticsListAdapter.setData(playerData.statistics)
    }
}