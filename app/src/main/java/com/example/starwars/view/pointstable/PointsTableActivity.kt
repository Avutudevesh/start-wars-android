package com.example.starwars.view.pointstable

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.utils.Utils
import com.example.starwars.view.matches.MatchesActivity
import com.example.starwars.view.pointstable.adapter.PointsTableAdapter
import com.example.starwars.viewmodel.StarWarsTournamentViewModel

class PointsTableActivity : AppCompatActivity() {

    private lateinit var adapter: PointsTableAdapter

    private lateinit var viewModel: StarWarsTournamentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[StarWarsTournamentViewModel::class.java]
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val pointsList = findViewById<RecyclerView>(R.id.points_table)
        adapter = PointsTableAdapter()
        pointsList.layoutManager = LinearLayoutManager(this)
        pointsList.adapter = adapter
        val matchesJson = Utils.getJsonDataFromAsset(this, "StarWarsMatches.json")
        val playersJson = Utils.getJsonDataFromAsset(this, "StarWarsPlayers.json")
        adapter.playerScores = viewModel.getPointsTable(playersJson, matchesJson)
        adapter.clickLiveData.observe(this){
            startActivity(MatchesActivity.getIntent(this, it))
        }
    }
}