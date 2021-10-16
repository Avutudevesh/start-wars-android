package com.example.starwars.repository

import com.example.starwars.models.Match
import com.example.starwars.models.Player
import com.example.starwars.models.PlayerMatch
import com.example.starwars.models.PlayerScore
import com.example.starwars.models.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object StarWarsTournamentRepository {

    private val gson = Gson()
    private lateinit var players: Map<Int,PlayerScore>
    private lateinit var matches: Map<id, Match>

    suspend fun getPointsTable(playersJson: String?, matchesJson: String?): List<PlayerScore> {
        withContext(Dispatchers.Default){
        players = getPlayers(playersJson)
        val playersScores: MutableMap<Int, Int> = players.map { it.id to 0 }.toMap(mutableMapOf())
        matches = getMatches(matchesJson)
        for (match in matches) {
            when {
                match.player1.score > match.player2.score -> {
                    updateScore(playersScores, match.player1.id, 3)
                }
                match.player1.score < match.player2.score -> {
                    updateScore(playersScores, match.player2.id, 3)
                }
                else -> {
                    updateScore(playersScores, match.player1.id, 1)
                    updateScore(playersScores, match.player2.id, 1)
                }
            }
        }
        }

        return players.map { player ->
            val ps = PlayerScore(player, playersScores[player.id] ?: 0)

            players.put(player.id, ps)
            ps
        }

    }

    fun getPlayerMatches(id: Int): List<PlayerMatch> {
        val playerMatches: MutableList<PlayerMatch> = mutableListOf()
        matches[id]
        matches.map { match ->
            if (match.player1.id == id || match.player2.id == id) {
//                if(match.player1.score > match.player2.score){
//                    if(match.player1.id ==id){
//                        Result.WIN
//                    }
//                }
                playerMatches.add(
                    PlayerMatch(
                        players[id].player,
                        players.[id].player,
                        match.player1.score,
                        match.player2.score
                    )
                )
            }
        }
        return playerMatches
    }

    private fun getMatches(matchesJson: String?): List<Match> {
        val listMatchType = object : TypeToken<List<Match>>() {}.type
        return gson.fromJson(matchesJson, listMatchType)
    }

    private fun getPlayers(playersJson: String?): List<Player> {
        val listMatchType = object : TypeToken<List<Player>>() {}.type
        return gson.fromJson(playersJson, listMatchType)
    }

    private fun updateScore(playerScores: MutableMap<Int, Int>, playerId: Int, pointsGained: Int) {
        val previousScore = playerScores[playerId] ?: 0
        playerScores[playerId] = previousScore + pointsGained
    }
}