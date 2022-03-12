package com.example.soccerholic.ui.detail.player

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.soccerholic.R
import com.example.soccerholic.base.BaseFragment
import com.example.soccerholic.databinding.FragmentPlayerDetailBinding
import com.example.soccerholic.ui.search.SearchViewModel


class PlayerDetailFragment : BaseFragment<FragmentPlayerDetailBinding>(R.layout.fragment_player_detail) {
    private val args by navArgs<PlayerDetailFragmentArgs>()
    private val searchViewModel: SearchViewModel by activityViewModels()

    override fun init() {

    }
}