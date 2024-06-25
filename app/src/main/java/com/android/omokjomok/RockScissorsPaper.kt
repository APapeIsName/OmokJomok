package com.android.omokjomok

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

interface SelectedRSP
class Rock : SelectedRSP
class Scissor : SelectedRSP
class Paper : SelectedRSP

fun SelectedRSP.versus(rsp: SelectedRSP): Int {
    return when {
        this::class == rsp::class -> 0
        (this is Rock && rsp is Scissor)
                || (this is Scissor && rsp is Paper)
                || (this is Paper && rsp is Rock)
        -> 1
        else -> -1
    }
}

@Composable
fun RSPGroup(context: Context, rsp: SelectedRSP?, onChange: (SelectedRSP?) -> Unit) {
    val drawingRock = painterResource(id = R.drawable.rock)
    val drawingScissors = painterResource(id = R.drawable.scissors)
    val drawingPaper = painterResource(id = R.drawable.paper)
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = drawingRock,
            contentDescription = "Rock",
            Modifier
                .size(100.dp)
                .clickable {
                    onChange(Rock())
                }
        )
        Image(
            painter = drawingScissors,
            contentDescription = "Scissors",
            Modifier
                .size(100.dp)
                .clickable {
                    onChange(Scissor())
                }
        )
        Image(
            painter = drawingPaper,
            contentDescription = "Paper",
            Modifier
                .size(100.dp)
                .clickable {
                    onChange(Paper())
                }
        )

    }
}