package com.android.omokjomok

interface Game {
    val players: ArrayList<Player>
    fun getGame() = this
    fun setPlayers(playerNum: Int)
    fun getPlayers() : ArrayList<Player>
}

class RockScissorPaper(override val players: ArrayList<Player>) : Game {

}