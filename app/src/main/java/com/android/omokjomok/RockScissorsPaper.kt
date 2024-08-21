package com.android.omokjomok

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

const val RSP_VS_WIN = 1
const val RSP_VS_DRAW = 0
const val RSP_VS_LOSE = -1

interface SelectedRsp
class Rock : SelectedRsp
class Scissor : SelectedRsp
class Paper : SelectedRsp

infix fun SelectedRsp.versus(rsp: SelectedRsp): Int {
    return when {
        this::class == rsp::class -> RSP_VS_DRAW
        (this is Rock && rsp is Scissor)
                || (this is Scissor && rsp is Paper)
                || (this is Paper && rsp is Rock)
        -> RSP_VS_WIN
        else -> RSP_VS_LOSE
    }
}

@Composable
fun RspGroup(onChange: (SelectedRsp?) -> Unit) {
    val drawingRock = painterResource(id = R.drawable.rock)
    val drawingScissors = painterResource(id = R.drawable.scissors)
    val drawingPaper = painterResource(id = R.drawable.paper)
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RspImage(painter = drawingRock) { onChange(Rock()) }
        RspImage(painter = drawingScissors) { onChange(Scissor()) }
        RspImage(painter = drawingPaper) { onChange(Paper()) }
    }
}

@Composable
fun RspImage(painter: Painter, onChange: () -> Unit) {
    Image(
        painter = painter,
        contentDescription = "Paper",
        Modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(1.dp, Color.Black, CircleShape)
            .padding(3.dp)
            .shadow(16.dp, CircleShape)
            .background(LocalContentColor.current)
            .clickable {
                onChange()
            }
    )
}

fun textChange(result: Int): Pair<String, String> {
    val firstString: String
    val secondString: String
    when (result) {
        RSP_VS_WIN -> {
            firstString = "승리!"
            secondString = "패배..."
        }

        RSP_VS_LOSE -> {
            firstString = "패배..."
            secondString = "승리!"
        }

        else -> {
            firstString = "무승부"
            secondString = "무승부"
        }
    }
    return Pair(firstString, secondString)
}