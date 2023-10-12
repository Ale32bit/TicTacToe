package me.alexdevs.tictactoe.feature_tictactoe.presentation.game

import androidx.compose.runtime.mutableStateListOf

class GameRound {
    public enum class Player {
        None,
        Circle,
        Cross,
    }

    public var grid = mutableStateListOf(
        Player.None, Player.None, Player.None,
        Player.None, Player.None, Player.None,
        Player.None, Player.None, Player.None,
    )

    public var playerTurn: Player = Player.Cross
        get() = field
        private set;

    public var winner: Player = Player.None
        get() = field
        private set

    public fun playTurn(x: Int, y: Int): Boolean {
        return playTurn(y * 3 + x)
    }

    public fun playTurn(cell: Int): Boolean {
        if(grid[cell] != Player.None) {
            throw Exception("Attempt to override already played cell.")
        }

        grid[cell] = playerTurn;

        val hasPlayerWon = hasWon(playerTurn);
        winner = playerTurn;

        playerTurn = if(playerTurn == Player.Cross) Player.Circle else Player.Cross

        return hasPlayerWon;
    }

    public fun isDraw(): Boolean {
        if(hasWon(Player.Cross) || hasWon(Player.Circle))
            return false

        for(cell in grid) {
            if (cell == Player.None)
                return false
        }

        return true;
    }

    private fun getCell(x: Int, y: Int): Int {
        return y * 3 + x
    }

    public fun hasWon(player: Player): Boolean {

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

        // Controllo della diagonale principale
        if (grid[0] == player && grid[4] == player && grid[8] == player) {
            return true
        }

        // Controllo della diagonale secondaria
        if (grid[2] == player && grid[4] == player && grid[6] == player) {
            return true
        }

        return false
    }

    public fun canPlayCell(cell: Int): Boolean =
        grid[cell] == Player.None
    public fun canPlayCell(x: Int, y: Int): Boolean =
        grid[x + y * 3] == Player.None
}