package com.example.soccerholic.ui.detail.player

import androidx.navigation.fragment.navArgs
import com.example.soccerholic.R
import com.example.soccerholic.base.BaseFragment
import com.example.soccerholic.databinding.FragmentPlayerDetailBinding


class PlayerDetailFragment : BaseFragment<FragmentPlayerDetailBinding>(R.layout.fragment_player_detail) {
    private val args by navArgs<PlayerDetailFragmentArgs>()

    override fun init() {
        binding.textTemp.text = args.playerId.toString()
    }
}