package com.example.starwars.view.pointstable.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.models.PlayerScore
import com.example.starwars.utils.Utils.loadImage

class PointsTableAdapter : RecyclerView.Adapter<PointsTableViewHolder>() {

    val clickLiveData: MutableLiveData<Int> = MutableLiveData()

    var playerScores: List<PlayerScore> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointsTableViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_points_table, parent, false)
        return PointsTableViewHolder(view)
    }

    override fun onBindViewHolder(holder: PointsTableViewHolder, position: Int) {
        holder.bind(playerScores[position], clickLiveData)
    }

    override fun getItemCount() = playerScores.size
}

class PointsTableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val container: View = itemView.findViewById(R.id.container)
    private val playerAvatar: ImageView = itemView.findViewById(R.id.avatar)
    private val playerName: TextView = itemView.findViewById(R.id.name)
    private val playerScoreView: TextView = itemView.findViewById(R.id.points)

    fun bind(playerScore: PlayerScore, liveData: MutableLiveData<Int>) {
        container.setOnClickListener {
            liveData.value = playerScore.player.id
        }
        playerName.text = playerScore.player.name
        playerScoreView.text = playerScore.score.toString()
        playerAvatar.loadImage(playerScore.player.icon)
    }
}
