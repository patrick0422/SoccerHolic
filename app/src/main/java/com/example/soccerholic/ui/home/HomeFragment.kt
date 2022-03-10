package com.example.soccerholic.ui.home

import androidx.fragment.app.viewModels
import com.example.soccerholic.R
import com.example.soccerholic.base.BaseFragment
import com.example.soccerholic.databinding.FragmentHomeBinding
import com.example.soccerholic.ui.search.SearchViewModel
import com.example.soccerholic.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val searchViewModel: SearchViewModel by viewModels()
    private val favoriteTeamAdapter = FavoriteTeamAdapter()

    override fun init() = with(binding) {
        favoriteTeamRecyclerView.adapter = favoriteTeamAdapter


        // Temporary code to test recyclerview
        if (favoriteTeamAdapter.isListEmpty)
            searchViewModel.searchTeamWithKeyWord("manchester")
        searchViewModel.searchData.observe(requireActivity()) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    favoriteTeamAdapter.setData(result.data!!.response)
                }
                is NetworkResult.Error -> {
                    makeToast(result.message!!)
                }
                is NetworkResult.Loading -> {
                }
            }
        }

    }
}
