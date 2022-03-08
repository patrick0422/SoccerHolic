package com.example.soccerholic.ui.main.search

import android.text.Html
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.soccerholic.R
import com.example.soccerholic.base.BaseFragment
import com.example.soccerholic.databinding.FragmentSearchBinding
import com.example.soccerholic.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val searchViewModel: SearchViewModel by viewModels()
    private val resultListAdapter = SearchResultListAdapter()

    override fun init() = with(binding) {
        searchResultList.adapter = resultListAdapter

        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean = searchTeamWithKeyWord(text)
            override fun onQueryTextChange(newText: String?): Boolean = true
        })
    }

    fun searchTeamWithKeyWord(keyWord: String?): Boolean {
        if (!(keyWord!!.matches(Regex("[a-zA-Z0-9]")))) {
            makeToast("알파벳과 숫자만 입력 가능합니다")
            return true
        }

        if (keyWord!!.length < 3) {
            makeToast("3글자 이상 입력해 주세요")
            return true
        }

        searchViewModel.searchTeamWithKeyWord(keyWord)
        searchViewModel.searchResponse.observe(requireActivity()) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    isLoading(false)

                    isEmpty(result.data!!.teamResponse.isEmpty())

                    binding.textResult.text = Html.fromHtml(
                        getString(
                            R.string.search_result,
                            result.data.parameters.search,
                            result.data.teamResponse.size
                        ),
                        Html.FROM_HTML_MODE_LEGACY
                    )
                    resultListAdapter.setData(result.data.teamResponse)
                }
                is NetworkResult.Error -> {
                    isLoading(false)
                    makeToast(result.message!!)
                }
                is NetworkResult.Loading -> {
                    isLoading(true)
                }
            }
        }
        return true
    }

    private fun isEmpty(isEmpty: Boolean) = with(binding) {
        if (isEmpty) {
            imageError.visibility = View.VISIBLE
            textError.visibility = View.VISIBLE
            searchResultList.visibility = View.INVISIBLE
        } else {
            imageError.visibility = View.INVISIBLE
            textError.visibility = View.INVISIBLE
            searchResultList.visibility = View.VISIBLE
        }
    }

    private fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loading.visibility = View.VISIBLE
            binding.textResult.visibility = View.GONE
            isEmpty(false)
        }
        else {
            binding.loading.visibility = View.GONE
            binding.textResult.visibility = View.VISIBLE
        }
    }
}