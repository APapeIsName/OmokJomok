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

@Composable
fun RSPGroup(context: Context) {
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
                    Toast
                        .makeText(context, "바위", Toast.LENGTH_SHORT)
                        .show()
                }
        )
        Image(
            painter = drawingScissors,
            contentDescription = "Scissors",
            Modifier
                .size(100.dp)
                .clickable {
                    Toast
                        .makeText(context, "가위", Toast.LENGTH_SHORT)
                        .show()
                }
        )
        Image(
            painter = drawingPaper,
            contentDescription = "Paper",
            Modifier
                .size(100.dp)
                .clickable {
                    Toast
                        .makeText(context, "보", Toast.LENGTH_SHORT)
                        .show()
                }
        )

    }
}