package com.android.omokjomok

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
    Button(onClick = onButtonClick) {
        Text(text = "가위바위보")
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
    Column(
        Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        RSPGroup(context = context)
        Text(text = "VS", fontSize = 30.sp)
        RSPGroup(context = context)
    }
}

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


