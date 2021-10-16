package com.example.starwars.models

data class Player(val id: Int, val name: String, val icon: String)

data class PlayerScore(val player: Player, val score: Int)
