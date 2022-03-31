package com.patrick0422.soccerholic.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.patrick0422.soccerholic.base.BaseDiffUtil
import com.patrick0422.soccerholic.data.remote.response.TeamData
import com.patrick0422.soccerholic.databinding.ItemFavoriteTeamListBinding

class FavoriteTeamAdapter: RecyclerView.Adapter<FavoriteTeamAdapter.FavoriteTeamViewHolder>() {
    private var favoriteTeamList: List<TeamData> = emptyList()
    val isListEmpty get() = favoriteTeamList.isEmpty()

    class FavoriteTeamViewHolder(private val binding: ItemFavoriteTeamListBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): FavoriteTeamViewHolder =
                FavoriteTeamViewHolder(
                    ItemFavoriteTeamListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
        }

        fun bind(teamData: TeamData) = with(binding) {

            Glide
                .with(root)
                .load(teamData.team.logo)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageTeamLogo)
            textTeamName.text = teamData.team.name

            favoriteCard.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToTeamDetailFragment(teamData.team.id)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamViewHolder =
        FavoriteTeamViewHolder.from(parent)

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) = holder.bind(favoriteTeamList[position])

    override fun getItemCount(): Int = favoriteTeamList.size

    fun setData(newData: List<TeamData>) {
        val favoriteTeamListDiffUtil = BaseDiffUtil(favoriteTeamList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(favoriteTeamListDiffUtil)

        favoriteTeamList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}