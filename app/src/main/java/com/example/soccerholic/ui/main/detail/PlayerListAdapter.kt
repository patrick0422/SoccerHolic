package com.example.soccerholic.ui.main.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.soccerholic.R
import com.example.soccerholic.base.BaseDiffUtil
import com.example.soccerholic.data.model.Player
import com.example.soccerholic.databinding.ItemPlayerListBinding

class PlayerListAdapter : RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>() {
    private var playerList: List<Player> = emptyList()

    class PlayerViewHolder(private val binding: ItemPlayerListBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): PlayerViewHolder =
                PlayerViewHolder(ItemPlayerListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

        fun bind(player: Player) = with(binding) {
            textPlayerName.text = player.name
            textPlayerAge.text = player.age.toString()
            textPlayerNumber.text = binding.root.context.getString(R.string.player_number, player.number)
            textPlayerPosition.text = player.position
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder =
        PlayerViewHolder.from(parent)

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) =
        holder.bind(playerList[position])

    override fun getItemCount(): Int = playerList.size

    fun setData(newData: List<Player>) {
        val playerListDiffUtil = BaseDiffUtil<Player>(playerList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(playerListDiffUtil)

        playerList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}