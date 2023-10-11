package me.alexdevs.tictactoe.feature_tictactoe.presentation.game

class GameRound {
    public enum class Player {
        None,
        Circle,
        Cross,
    }

    public var grid = arrayOf(
        arrayOf(Player.None, Player.None, Player.None),
        arrayOf(Player.None, Player.None, Player.None),
        arrayOf(Player.None, Player.None, Player.None),
    )

    public var playerTurn: Player = Player.Cross
        get() = field
        private set;

    public fun playTurn(x: Int, y: Int): Boolean {
        if(grid[y][x] != Player.None) {
            throw Exception("Attempt to override already played cell.")
        }

        grid[y][x] = playerTurn;

        val hasPlayerWon = hasWon(playerTurn);

        playerTurn = if(playerTurn == Player.Cross) Player.Circle else Player.Cross

        return hasPlayerWon;
    }

    public fun isDraw(): Boolean {
        if(hasWon(Player.Cross) || hasWon(Player.Circle))
            return false

        for(y in 0..2) {
            for(x in 0..2) {
                if(grid[y][x] == Player.None)
                    return false;
            }
        }

        return true;
    }

    public fun hasWon(player: Player): Boolean {
        // Controllo delle righe
        for (row in grid) {
            if (row.all { it == player }) {
                return true
            }
        }

        // Controllo delle colonne
        for (col in 0 until grid.size) {
            if (grid.all { it[col] == player }) {
                return true
            }
        }

        // Controllo della diagonale principale
        if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {
            return true
        }

        // Controllo della diagonale secondaria
        if (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player) {
            return true
        }

        return false
    }

    public fun canPlayCell(x: Int, y: Int): Boolean =
        grid[y][x] == Player.None
}