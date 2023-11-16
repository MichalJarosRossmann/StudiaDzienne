package pl.studia.studiadzienne.restaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeScreen()
        }
    }
}

@Composable
fun ComposeScreen() {

    val textState = remember {
        mutableStateOf<String>("")
    }
    Surface(modifier = Modifier.fillMaxSize()) {


        Row(
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Text(
                text = textState.value,
                modifier = Modifier
                    .background(Color.Gray)
                    .size(100.dp)
            )

            LazyColumn {

                item {
                    Text(fontSize = 16.sp,
                        text = "Tytuł listy")
                }

                for (i in 0..10000) {
                    item {  DrawRow()}
                }
            }

            Button(onClick = { textState.value = "lorem ipsum ${Random.nextInt()}" }) {

            }
        }


    }


}

@Composable
fun DrawRow() {
    Row {
        Text(text = "description")
        Text(text = "title")
    }
}

@Composable
fun DrawButtons() {


}


@Preview(showBackground = true)
@Composable
fun ComposePreview() {
    ComposeScreen()
}

@Preview(showBackground = true)
@Composable
fun ComposePreview2() {
    DrawButtons()
}