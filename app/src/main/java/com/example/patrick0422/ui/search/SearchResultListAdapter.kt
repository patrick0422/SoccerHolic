package com.example.soccerholic.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.soccerholic.base.BaseDiffUtil
import com.example.soccerholic.data.remote.model.TeamData
import com.example.soccerholic.databinding.ItemSearchResultListBinding

class SearchResultListAdapter : RecyclerView.Adapter<SearchResultListAdapter.SearchResultViewHolder>() {
    var searchResultList = listOf<TeamData>()

    class SearchResultViewHolder(
        private val binding: ItemSearchResultListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup) =
                SearchResultViewHolder(ItemSearchResultListBinding.inflate(LayoutInflater.from(parent.context), parent,false))
        }

        fun bind(teamData: TeamData) = with(binding) {
            Glide
                .with(binding.root)
                .load(teamData.team.logo)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(teamImage)
            teamName.text = teamData.team.name
            teamCard.setOnClickListener {
                val action = SearchFragmentDirections.actionSearchFragmentToTeamDetailFragment(teamData.team.id)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder = SearchResultViewHolder.from(parent)

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) = holder.bind(searchResultList[position])

    override fun getItemCount(): Int = searchResultList.size

    fun setData(newData: List<TeamData>) {
        val searchResultListDiffUtil = BaseDiffUtil(searchResultList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(searchResultListDiffUtil)

        searchResultList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}

