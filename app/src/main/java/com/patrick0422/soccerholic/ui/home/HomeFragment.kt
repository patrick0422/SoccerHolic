package com.patrick0422.soccerholic.ui.home

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.patrick0422.soccerholic.R
import com.patrick0422.soccerholic.base.BaseFragment
import com.patrick0422.soccerholic.databinding.FragmentHomeBinding
import com.patrick0422.soccerholic.ui.MainViewModel
import com.patrick0422.soccerholic.ui.search.SearchViewModel
import com.patrick0422.soccerholic.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val mainViewModel: MainViewModel by activityViewModels()
    private val favoriteTeamAdapter = FavoriteTeamAdapter()

    override fun init() = with(binding) {
        favoriteTeamRecyclerView.adapter = favoriteTeamAdapter

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        mainViewModel.readTeamBookmark.observe(viewLifecycleOwner) { result ->
            favoriteTeamAdapter.setData(result.map { it.favoriteTeam })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_goto_search -> {
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
