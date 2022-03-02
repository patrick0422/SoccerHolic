package com.example.soccerholic.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.soccerholic.R
import com.example.soccerholic.data.search.SearchResponse
import com.example.soccerholic.databinding.ItemSearchResultListBinding

class SearchResultListAdapter : RecyclerView.Adapter<SearchResultViewHolder>() {
    val searchResultList = listOf<SearchResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder = SearchResultViewHolder.from(parent)

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(searchResultList[position])
    }

    override fun getItemCount(): Int = searchResultList.size

}

class SearchResultViewHolder(
    private val binding: ItemSearchResultListBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup) = SearchResultViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_search_result_list,
                parent,
                false
            )
        )
    }

    fun bind(searchResponse: SearchResponse) = with(binding) {
        // TODO
    }
}