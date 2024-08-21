package com.android.omokjomok

interface Game {
    val number: Int
    fun play()
}

class RockScissorPaper(private val player1: RspPlayer, private val player2: RspPlayer) : Game {
    override val number: Int = 2

    override fun play() {
        player1.selectRsp() versus player2.selectRsp()
    }
}