package com.patrick0422.soccerholic.ui

import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.patrick0422.soccerholic.R
import com.patrick0422.soccerholic.base.BaseActivity
import com.patrick0422.soccerholic.databinding.ActivityMainBinding
import com.patrick0422.soccerholic.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private val navController by lazy { findNavController(R.id.fragmentContainerView) }

    override fun init(): Unit = with(binding) {
        setSupportActionBar(toolbar)

//        mainViewModel.readTeamBookmark().asLiveData().observe(this@MainActivity) {
//
//        }
    }

    override fun onBackPressed() {
        binding.toolbar.visibility = View.VISIBLE

        if (!navController.navigateUp())
            super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main_toolbar, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_search -> {
                navController.navigate(R.id.action_homeFragment_to_searchFragment)
                binding.toolbar.visibility = View.GONE
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}