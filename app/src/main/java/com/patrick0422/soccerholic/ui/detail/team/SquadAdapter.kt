package com.patrick0422.soccerholic.ui.detail.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.patrick0422.soccerholic.R
import com.patrick0422.soccerholic.base.BaseDiffUtil
import com.patrick0422.soccerholic.data.remote.model.Player
import com.patrick0422.soccerholic.databinding.ItemSquadBinding

class SquadAdapter : RecyclerView.Adapter<SquadAdapter.SquadViewHolder>() {
    private var squad: List<Player> = emptyList()

    class SquadViewHolder(private val binding: ItemSquadBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): SquadViewHolder =
                SquadViewHolder(ItemSquadBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

        fun bind(player: Player) = with(binding) {
            Glide
                .with(root)
                .load(player.photo)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imagePlayerProfile)
            textPlayerName.text = player.name
            textPlayerAge.text =  binding.root.context.getString(R.string.player_age, player.age)
            textPlayerNumber.text = binding.root.context.getString(R.string.player_number, player.number)
            textPlayerPosition.text = player.position

            playerCard.setOnClickListener {
                val action = TeamDetailFragmentDirections.actionTeamDetailFragmentToPlayerDetailFragment(player.id)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquadViewHolder =
        SquadViewHolder.from(parent)

    override fun onBindViewHolder(holder: SquadViewHolder, position: Int) =
        holder.bind(squad[position])

    override fun getItemCount(): Int = squad.size

    fun setData(newData: List<Player>) {
        val playerListDiffUtil = BaseDiffUtil<Player>(squad, newData)
        val diffUtilResult = DiffUtil.calculateDiff(playerListDiffUtil)

        squad = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}