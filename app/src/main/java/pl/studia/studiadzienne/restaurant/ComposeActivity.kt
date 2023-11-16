package pl.studia.studiadzienne.restaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textState= mutableStateOf<String>("")


        setContent {
            ComposeScreen(textState)
        }
    }
}

@Composable
fun ComposeScreen(textState: MutableState<String>){
    Surface(modifier = Modifier.fillMaxSize()) {

        Row(horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = textState.value,
                modifier = Modifier
                    .background(Color.Gray)
                    .size(100.dp)
            )
            Button(onClick = { textState.value="lorem ipsum ${Random.nextInt()}" }) {

            }
        }



    }


}
@Composable
fun DrawButtons() {


}


@Preview(showBackground = true)
@Composable
fun ComposePreview() {

    ComposeScreen(remember {
        mutableStateOf<String>("aaa")
    })
}

@Preview(showBackground = true)
@Composable
fun ComposePreview2() {
    DrawButtons()
}