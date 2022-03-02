package com.example.soccerholic.ui.main.search

import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.soccerholic.R
import com.example.soccerholic.base.BaseFragment
import com.example.soccerholic.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val searchViewModel : SearchViewModel by viewModels()

    override fun init() = with(binding) {
        searchView.isSubmitButtonEnabled = true
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                if (text.isNullOrBlank()) {
                    makeToast("검색어를 입력해 주세요")


                }
                else {

                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })
    }


}