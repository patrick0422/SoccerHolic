package com.example.soccerholic.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.soccerholic.base.BaseDiffUtil
import com.example.soccerholic.data.search.response.result.TeamResponse
import com.example.soccerholic.databinding.ItemSearchResultListBinding

class SearchResultListAdapter : RecyclerView.Adapter<SearchResultListAdapter.SearchResultViewHolder>() {
    var searchResultList = listOf<TeamResponse>()

    class SearchResultViewHolder(
        private val binding: ItemSearchResultListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup) =
                SearchResultViewHolder(ItemSearchResultListBinding.inflate(LayoutInflater.from(parent.context), parent,false))
        }

        fun bind(teamResponse: TeamResponse) = with(binding) {
            Glide
                .with(binding.root)
                .load(teamResponse.team.logo)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(teamImage)
            teamName.text = teamResponse.team.name
            teamCard.setOnClickListener {
                val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(teamResponse.team.id)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder = SearchResultViewHolder.from(parent)

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) = holder.bind(searchResultList[position])

    override fun getItemCount(): Int = searchResultList.size

    fun setData(newData: List<TeamResponse>) {
        val searchResultListDiffUtil = BaseDiffUtil(searchResultList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(searchResultListDiffUtil)

        searchResultList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}

