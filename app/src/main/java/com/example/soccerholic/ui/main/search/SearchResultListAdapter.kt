package com.example.soccerholic.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soccerholic.R
import com.example.soccerholic.base.BaseDiffUtil
import com.example.soccerholic.data.search.Response
import com.example.soccerholic.data.search.SearchResponse
import com.example.soccerholic.databinding.ItemSearchResultListBinding

class SearchResultListAdapter : RecyclerView.Adapter<SearchResultViewHolder>() {
    var searchResultList = listOf<Response>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder = SearchResultViewHolder.from(parent)

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) = holder.bind(searchResultList[position])

    override fun getItemCount(): Int = searchResultList.size

    fun setData(newData: List<Response>) {
        val searchResultListDiffUtil = BaseDiffUtil(searchResultList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(searchResultListDiffUtil)

        searchResultList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
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

    fun bind(response: Response) = with(binding) {
        Glide
            .with(binding.root)
            .load(response.team.logo)
            .into(teamImage)
        teamName.text = response.team.name

        teamCard.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(response.team.id)
            it.findNavController().navigate(action)
        }
    }
}