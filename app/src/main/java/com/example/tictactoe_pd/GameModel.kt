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
    val rnd = (0..8).random() // Генерируем случайное число от 1 до 9
    val emptyCells = filled.indices.filter { filled[it].isEmpty() } // Получаем список доступных ячеек

    if (emptyCells.isNotEmpty()) {
        val index = emptyCells.random() // Выбираем случайную доступную ячейку
        filled[index] = currentPlayer // Устанавливаем ход робота в выбранную ячейку
        currentPlayer = if (currentPlayer == "X") "O" else "X" // Переключаем текущего игрока
    }
}
}

enum class GameStat{
    CREATED,
    JOINED,
    INPROGRESS,
    FINISHED
}