package me.alexdevs.tictactoe

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

    public var playerTurn: Player = Player.None
        get() = field
        private set;

    public fun playTurn(x: Int, y: Int): Boolean {
        return true;
    }

    public fun isDraw(): Boolean {
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

}