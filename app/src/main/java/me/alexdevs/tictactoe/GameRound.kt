package me.alexdevs.tictactoe

class GameRound {
    public enum class Player {
        None,
        Circle,
        Cross,
    }

    public var grid = Array(3) {IntArray(3)}

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