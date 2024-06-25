package com.android.omokjomok

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.omokjomok.ui.theme.OmokJomokTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OmokJomokTheme {
                var screenState by remember { mutableIntStateOf(0) }
                val onButtonClick = {
                    screenState = 1
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (screenState) {
                        0 -> MainScreen(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding),
                            onButtonClick
                        )

                        1 -> GameScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    name: String,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onButtonClick) {
            Text(text = "가위바위보")
        }
    }
}

@Composable
fun GameScreen() {
    RSPScreen()
}

// 가위바위보 화면
@Composable
fun RSPScreen() {
    val context = LocalContext.current
    val selectedRSP: SelectedRSP? = null
    var firstState by remember { mutableStateOf(selectedRSP) }
    var secondState by remember { mutableStateOf(selectedRSP) }
    var firstString by remember { mutableStateOf("선택 중...") }
    var secondString by remember { mutableStateOf("선택 중...") }
    val onFirstChange = { rsp: SelectedRSP? ->
        firstState = rsp
        firstString = "선택 완료!"
        if(secondState != null)
            when(firstState?.versus(secondState!!)) {
                1 -> {
                    firstString = "승리!"
                    secondString = "패배..."
                }
                -1 -> {
                    firstString = "패배..."
                    secondString = "승리!"
                }
                else -> {
                    firstString = "무승부"
                    secondString = "무승부"
                }
            }
    }
    val onSecondChange = { rsp: SelectedRSP? ->
        secondState = rsp
        secondString = "선택 완료!"
        if(firstState != null)
            when(firstState?.versus(secondState!!)) {
                1 -> {
                    firstString = "승리!"
                    secondString = "패배..."
                }
                -1 -> {
                    firstString = "패배..."
                    secondString = "승리!"
                }
                else -> {
                    firstString = "무승부"
                    secondString = "무승부"
                }
            }
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        RSPGroup(context = context, firstState, onFirstChange)
        Text(text = firstString, fontSize = 40.sp)
        Text(text = "VS", fontSize = 30.sp)
        Text(text = secondString, fontSize = 40.sp)
        RSPGroup(context = context, secondState, onSecondChange)
    }
}

//가위 < 바위 < 보 < 가위

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun GreetingPreview() {
    OmokJomokTheme {
        MainScreen("Android", onButtonClick = {})
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RSPPreview() {
    OmokJomokTheme {
        RSPScreen()
    }
}


