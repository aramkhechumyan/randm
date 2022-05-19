package com.task.rickandmorty.presentation.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.task.rickandmorty.R
import com.task.rickandmorty.data.networck.dto.EpisodeDto

class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {

    private val episodes: MutableList<EpisodeDto> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name = view.findViewById<TextView>(R.id.episode_text)
        private val airDate = view.findViewById<TextView>(R.id.air_date)

        fun bindData(episodeDto: EpisodeDto) {
            name.text = episodeDto.name
            airDate.text = episodeDto.airDate
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.episode_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(episodes[position])
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    fun setItems(episodes: List<EpisodeDto>) {
        this.episodes.apply {
            clear()
            addAll(episodes)
        }
        notifyDataSetChanged()
    }
}