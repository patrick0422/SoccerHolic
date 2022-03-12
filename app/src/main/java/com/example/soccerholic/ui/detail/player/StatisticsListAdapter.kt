package com.example.soccerholic.ui.detail.player

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.soccerholic.base.BaseDiffUtil
import com.example.soccerholic.data.remote.model.DetailedPlayerData
import com.example.soccerholic.data.remote.model.temp.Statistic
import com.example.soccerholic.databinding.ItemPlayerStatisticsBinding

class StatisticsListAdapter: RecyclerView.Adapter<StatisticsListAdapter.StatisticsViewHolder>() {
    private var statistics: List<Statistic> = emptyList()

    class StatisticsViewHolder(private val binding: ItemPlayerStatisticsBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): StatisticsViewHolder = StatisticsViewHolder(
                ItemPlayerStatisticsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }

        fun bind(statistic: Statistic) = with(binding) {
            Glide.with(root)
                .load(statistic.league.logo)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageLeagueLogo)
            textLeagueName.text = statistic.league.name

            textAppearances.text = statistic.games.appearences.toString()
            textRating.text = statistic.games.rating

            textTotalPasses.text = statistic.passes.total.toString()
            textKeyPasses.text = statistic.passes.key.toString()
            textPassAccuracy.text = statistic.passes.accuracy.toString()

            textTotalShots.text = statistic.shots.total.toString()
            textOnTargetShots.text = statistic.shots.on.toString()

            textTotalGoals.text = statistic.goals.total.toString()
            textTotalAssists.text = statistic.goals.assists.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsViewHolder = StatisticsViewHolder.from(parent)

    override fun onBindViewHolder(holder: StatisticsViewHolder, position: Int) = holder.bind(statistics[position])

    override fun getItemCount(): Int = statistics.size

    fun setData(newData: List<Statistic>) {
        val statisticDiffUtil = BaseDiffUtil<Statistic>(statistics, newData)
        val result = DiffUtil.calculateDiff(statisticDiffUtil)

        statistics = newData
        result.dispatchUpdatesTo(this)
    }
}