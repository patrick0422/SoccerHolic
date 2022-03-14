package com.patrick0422.soccerholic.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.patrick0422.soccerholic.R
import com.patrick0422.soccerholic.base.BaseFragment
import com.patrick0422.soccerholic.databinding.FragmentHomeBinding
import com.patrick0422.soccerholic.ui.search.SearchViewModel
import com.patrick0422.soccerholic.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val searchViewModel: SearchViewModel by activityViewModels()
    private val favoriteTeamAdapter = FavoriteTeamAdapter()

    override fun init() = with(binding) {
        favoriteTeamRecyclerView.adapter = favoriteTeamAdapter

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        // Temporary code to test recyclerview
        if (favoriteTeamAdapter.isListEmpty)
            searchViewModel.searchTeamWithKeyWord("manchester")
        searchViewModel.searchData.observe(viewLifecycleOwner) { result ->
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
