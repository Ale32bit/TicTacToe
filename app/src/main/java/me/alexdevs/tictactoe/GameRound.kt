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
        return true;
    }
}