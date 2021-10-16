package com.example.starwars.viewmodel

import androidx.lifecycle.ViewModel
import com.example.starwars.repository.StarWarsTournamentRepository

class StarWarsTournamentViewModel : ViewModel() {


    fun getPointsTable(playersJson: String?, matchesJson: String?) =
        StarWarsTournamentRepository.getPointsTable(playersJson, matchesJson)

    fun getMatches(id: String) = StarWarsTournamentRepository.getPlayerMatches(id)
}