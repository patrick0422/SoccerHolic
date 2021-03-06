package com.patrick0422.soccerholic.ui.search

import android.os.Build
import android.text.Html
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.patrick0422.soccerholic.R
import com.patrick0422.soccerholic.base.BaseFragment
import com.patrick0422.soccerholic.databinding.FragmentSearchBinding
import com.patrick0422.soccerholic.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val searchViewModel: SearchViewModel by activityViewModels()
    private val resultListAdapter = SearchResultListAdapter()

    override fun init() = with(binding) {
        searchResultList.adapter = resultListAdapter
        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun searchTeamWithKeyWord(keyWord: String): Boolean {
        if (!isKeyWordValid(keyWord)) {
            makeToast("알파벳과 숫자만 입력 가능합니다")
            return true
        }

        if (keyWord.length < 3) {
            makeToast("3글자 이상 입력해 주세요")
            return true
        }

        searchViewModel.searchTeamWithKeyWord(keyWord)
        searchViewModel.searchData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    isLoading(false)

                    isEmpty(result.data!!.response.isEmpty())

                    binding.textResult.text = Html.fromHtml(
                        getString(
                            R.string.search_result,
                            result.data.parameters.search,
                            result.data.response.size
                        ),
                        Html.FROM_HTML_MODE_LEGACY
                    )

                    resultListAdapter.setData(result.data.response)
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

    private fun isKeyWordValid(keyWord: String): Boolean = ((keyWord.matches(Regex("^[a-zA-Z0-9\\s]+\$"))))

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_search, menu)

        (menu.findItem(R.id.toolbar_search).actionView as SearchView).apply {
            maxWidth = Int.MAX_VALUE
            isIconifiedByDefault = false
            queryHint = "팀 검색"
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onQueryTextSubmit(text: String?): Boolean = searchTeamWithKeyWord(text ?: "")
                override fun onQueryTextChange(newText: String?): Boolean = true
            })
        }

    }
}