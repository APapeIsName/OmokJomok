package com.android.omokjomok

import kotlin.random.Random

//abstract class Player {
//    private var game: Game? = null
//    fun getGame(): Game? = game
//    fun setGame(selectGame: Game) {
//        game = selectGame
//    }
//}

//interface Human
//
//interface AI

interface Player

class RspPlayer: Player {
    private var rsp: SelectedRsp? = null
    fun setRsp(selectedRsp: SelectedRsp) {
        rsp = selectedRsp
    }
}

class Human(private val player: Player) : Player by player

class AI(private val player: Player) : Player by player

fun RspPlayer.random() {
    val random = Random(3)
    when(random.nextInt()) {
        0 -> this.setRsp(Rock())
        1 -> this.setRsp(Rock())
        2 -> this.setRsp(Rock())
    }
}

//class RspHuman: Player(), Human {
//
//}
//
//class RspAI: Player(), AI {
//
//}

//fun RspAI.random() {
//    val random = Random(3)
//    when(random.nextInt()) {
//        0 -> this.setRsp(Rock())
//        1 -> this.setRsp(Rock())
//        2 -> this.setRsp(Rock())
//    }
//}

interface Game

class RockScissorPaper: Game