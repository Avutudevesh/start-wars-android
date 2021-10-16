package com.example.starwars.view.matches

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.view.matches.adapter.MatchesAdapter
import com.example.starwars.view.pointstable.PointsTableActivity
import com.example.starwars.viewmodel.StarWarsTournamentViewModel

class MatchesActivity: AppCompatActivity() {

    private lateinit var adapter: MatchesAdapter

    private lateinit var viewModel: StarWarsTournamentViewModel

    companion object {
        private const val ID = "ID"
        fun getIntent(context: Context, id: Int): Intent {
            return Intent(context, MatchesActivity::class.java).apply {
                putExtra(ID, id)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches)
        viewModel = ViewModelProvider(this)[StarWarsTournamentViewModel::class.java]
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val matchesList = findViewById<RecyclerView>(R.id.matches_list)
        adapter = MatchesAdapter()
        matchesList.layoutManager = LinearLayoutManager(this)
        matchesList.adapter = adapter
        val id = intent.getIntExtra(ID, 1)
        adapter.matches = viewModel.getMatches(id)

    }
}