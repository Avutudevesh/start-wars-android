package com.example.starwars.view.matches.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.models.PlayerMatch

class MatchesAdapter : RecyclerView.Adapter<MatchesViewHolder>() {

    var matches: List<PlayerMatch> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_match, parent, false)
        return MatchesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.bind(matches[position])
    }

    override fun getItemCount() = matches.size
}

class MatchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val container: View = itemView.findViewById(R.id.match_container)
    private val player1: TextView = itemView.findViewById(R.id.player1)
    private val player2: TextView = itemView.findViewById(R.id.player2)
    private val score: TextView = itemView.findViewById(R.id.match_score)

    fun bind(match: PlayerMatch) {
        player1.text = match.player1.name
        player2.text = match.player2.name
        score.text = "${match.player1Score} - ${match.player1Score}"
    }

}

