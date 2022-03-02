package com.example.soccerholic.ui.main

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.navigation.findNavController
import com.example.soccerholic.R
import com.example.soccerholic.base.BaseActivity
import com.example.soccerholic.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val navController by lazy { findNavController(R.id.fragmentContainerView) }

    override fun init() = with(binding) {
        setSupportActionBar(toolbar)


    }

    override fun onBackPressed() {
        binding.toolbar.visibility = View.VISIBLE

        if (!navController.popBackStack())
            super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main_toolbar, menu)



        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
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