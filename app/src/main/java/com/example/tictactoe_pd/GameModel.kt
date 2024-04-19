package com.example.tictactoe_pd

import kotlin.random.Random

data class GameModel (
    val gameId : String = "-1",
    var filled : MutableList<String> = mutableListOf("","","","","","","","","",),
    var winner : String = "",
    var gameStat : GameStat = GameStat.CREATED,
    var currentPlayer : String = (arrayOf("X","O"))[Random.nextInt(2)]

){
fun robot() {
    val rnd = (0..8).random() // seit es meiginaju ieklaut tadu robota-funkciju, lai varetu spelet ar datoru
    val emptyCells = filled.indices.filter { filled[it].isEmpty() } 

    if (emptyCells.isNotEmpty()) {
        val index = emptyCells.random() 
        filled[index] = currentPlayer 
        currentPlayer = if (currentPlayer == "X") "O" else "X" 
    }
}
}

enum class GameStat{
    CREATED,
    JOINED,
    INPROGRESS,
    FINISHED
}
