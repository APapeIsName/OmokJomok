package com.android.omokjomok

import kotlin.random.Random

interface Player

open class RspPlayer(private val num: Int): Player {
    open fun selectRsp(): SelectedRsp {
        return when(num) {
            0 -> Rock()
            1 -> Scissor()
            else -> Paper()
        }
    }
}

class RspAI : RspPlayer(random(3))

class Human(private val player: Player) : Player by player

class AI(private val player: Player) : Player by player

fun random(n: Int): Int = Random(n).nextInt()