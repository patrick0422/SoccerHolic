package com.example.soccerholic.ui

import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import com.example.soccerholic.R
import com.example.soccerholic.base.BaseActivity
import com.example.soccerholic.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()
    private val navController by lazy { findNavController(R.id.fragmentContainerView) }

    override fun init() = with(binding) {
        setSupportActionBar(toolbar)

        mainViewModel.readTeamBookmark().asLiveData().observe(this@MainActivity) {
            
        }
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