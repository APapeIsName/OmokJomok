package com.android.omokjomok

import android.content.Context
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.omokjomok.ui.theme.OmokJomokTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

const val SCREEN_MAIN = 0
const val SCREEN_GAME = 1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OmokJomokTheme {
                /*TODO*/ // 인트를 게임으로 변경
                var screenState by remember { mutableIntStateOf(0) }
                val onButtonClick = {
                    screenState = 1
                }
                var endState by remember { mutableStateOf(false) }
                val onEnd = {
                    endState = false
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (screenState) {
                        SCREEN_MAIN -> MainScreen(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding),
                            onButtonClick
                        )
                        SCREEN_GAME -> GameScreen()
                    }
                }
            }
        }
    }
}

infix fun Int.plus(num:Int) {
    println(this + num)
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
    RspScreen(1)
}

// 가위바위보 화면
@Composable
fun RspScreen(playerNum: Int) {

    val selectedRsp: SelectedRsp? = null

    var firstState by remember { mutableStateOf(selectedRsp) }
    var firstString by remember { mutableStateOf("선택 중...") }

    var secondState by remember { mutableStateOf(selectedRsp) }
    var secondString by remember { mutableStateOf("선택 중...") }

    val onFirstChange = { rsp: SelectedRsp? ->
        firstState = rsp
        firstString = "선택 완료!"
        if (secondState != null) {
            val pair = textChange(firstState?.versus(secondState!!) ?: 0)
            firstString = pair.first
            secondString = pair.second
        }
    }
    val onSecondChange = { rsp: SelectedRsp? ->
        secondState = rsp
        secondString = "선택 완료!"
        if (firstState != null) {
            val pair = textChange(firstState?.versus(secondState!!) ?: 0)
            firstString = pair.first
            secondString = pair.second
        }
    }

    RspUI(
        onFirstChange = onFirstChange,
        onSecondChange = onSecondChange,
        firstString = firstString,
        secondString = secondString
    )
}

/*TODO*/ // 나중에 Game을 상태에 추가

// TEST test

@Composable
fun RspUI(
    onFirstChange: (SelectedRsp?) -> Unit,
    onSecondChange: (SelectedRsp?) -> Unit,
    firstString: String,
    secondString: String
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        CompositionLocalProvider(LocalContentColor.provides(Color.Red)) {
            RspGroup(onSecondChange)
        }
        Text(text = secondString, fontSize = 40.sp)
        Text(text = "VS", fontSize = 30.sp)
        Text(text = firstString, fontSize = 40.sp)
        CompositionLocalProvider(LocalContentColor.provides(Color.Blue)) {
            RspGroup(onFirstChange)
        }
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
fun RspPreview() {
    OmokJomokTheme {
        RspScreen(1)
    }
}
