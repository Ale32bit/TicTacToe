package me.alexdevs.tictactoe.feature_tictactoe.presentation.game

import androidx.compose.runtime.mutableStateListOf

class GameRound {
    enum class Player {
        None,
        Circle,
        Cross,
    }

    var grid = mutableStateListOf(
        Player.None, Player.None, Player.None,
        Player.None, Player.None, Player.None,
        Player.None, Player.None, Player.None,
    )

    var playerTurn: Player = Player.Cross
        private set

    var winner: Player = Player.None
        private set

    fun playTurn(x: Int, y: Int): Boolean =
        playTurn(y * 3 + x)


    fun playTurn(cell: Int): Boolean {
        if(grid[cell] != Player.None) {
            throw Exception("Attempt to override already played cell.")
        }

        grid[cell] = playerTurn

        val hasPlayerWon = hasWon(playerTurn)
        if(hasPlayerWon)
            winner = playerTurn

        playerTurn = if(playerTurn == Player.Cross) Player.Circle else Player.Cross

        return hasPlayerWon
    }

    fun isDraw(): Boolean {
        if(hasWon(Player.Cross) || hasWon(Player.Circle))
            return false

        for(cell in grid) {
            if (cell == Player.None)
                return false
        }

        return true
    }

    private fun getCell(x: Int, y: Int): Int = y * 3 + x

    fun hasWon(player: Player): Boolean {

        // Check if row cells have matching player
        for(y in 0 until 3) {
            if(grid[y * 3 + 0] == player &&
                grid[y * 3 + 1] == player &&
                grid[y * 3 + 2] == player)
                return true
        }

        // Check if columns cells have matching player
        for(x in 0 until 3) {
            if(grid[x + 0] == player &&
                grid[x + 3] == player &&
                grid[x + 6] == player)
                return true
        }

        // Check of first diagonal
        if (grid[0] == player && grid[4] == player && grid[8] == player) {
            return true
        }

        // Check of second diagonal
        if (grid[2] == player && grid[4] == player && grid[6] == player) {
            return true
        }

        return false
    }

    fun canPlayCell(cell: Int): Boolean =
        grid[cell] == Player.None
    fun canPlayCell(x: Int, y: Int): Boolean =
        grid[x + y * 3] == Player.None

    private fun minimax(depth: Int, isMaximizing: Boolean): Int {
        if (hasWon(Player.Cross)) {
            return -1
        }
        if (hasWon(Player.Circle)) {
            return 1
        }
        if (isDraw()) {
            return 0
        }

        if (isMaximizing) {
            var bestScore = Int.MIN_VALUE
            for (i in grid.indices) {
                if (grid[i] == Player.None) {
                    grid[i] = Player.Circle
                    val score = minimax(depth + 1, false)
                    grid[i] = Player.None
                    bestScore = maxOf(score, bestScore)
                }
            }
            return bestScore
        } else {
            var bestScore = Int.MAX_VALUE
            for (i in grid.indices) {
                if (grid[i] == Player.None) {
                    grid[i] = Player.Cross
                    val score = minimax(depth + 1, true)
                    grid[i] = Player.None
                    bestScore = minOf(score, bestScore)
                }
            }
            return bestScore
        }
    }

    fun bestMove(): Int {
        var bestScore = Int.MIN_VALUE
        var move = -1
        for (i in grid.indices) {
            if (grid[i] == Player.None) {
                grid[i] = Player.Circle
                val score = minimax(0, false)
                grid[i] = Player.None
                if (score > bestScore) {
                    bestScore = score
                    move = i
                }
            }
        }
        return move
    }
}