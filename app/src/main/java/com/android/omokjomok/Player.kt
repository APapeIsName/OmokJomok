package com.android.omokjomok

import kotlin.random.Random

interface Player {
    //val game: Game
    fun play(game: Game)
}

open class RspPlayer: Player {
    private var rsp: SelectedRsp? = null
    open fun setRsp(num: Int) {
        rsp = when(num) {
            0 -> Rock()
            1 -> Scissor()
            else -> Paper()
        }
    }

    override fun play(game: Game) {
        TODO("Not yet implemented")
    }
}

class RspAI : RspPlayer() {
    //override val game: Game = RockScissorPaper()
    private var rsp: SelectedRsp? = null
    override fun play(game: Game) {
        TODO("Not yet implemented")
    }
}

class Human(private val player: Player) : Player by player

class AI(private val player: Player) : Player by player
// 하고 있는 게임이 뭔지에 따라 다르게 하거나,
// player가 어떤 타입인지에 따라 when으로 다른 함수 호출
// 현재는 AI와 사람을 게임마다 클래스를 새로 만들어주는 식으로 하고 있음.
// 단일책임원칙에는 괜찮지만, 너무 많은 클래스가 생기지 않을까 걱정됨.

fun RspAI.random(): Int = Random(3).nextInt()

fun RspPlayer.select(num: Int) = setRsp(num)