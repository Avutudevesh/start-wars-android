package com.example.starwars.models

data class Match(val player1: PlayerMatchScore, val player2: PlayerMatchScore)

data class PlayerMatchScore(val id: Int, val score: Int)

data class PlayerMatch(val player1: Player, val player2: Player, val player1Score: Int, val player2Score: Int, val result: Result)

enum class Result {
    WIN,
    LOST,
    TIE
}