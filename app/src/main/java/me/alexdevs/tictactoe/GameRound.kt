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
        return true;
    }
}