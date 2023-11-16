package pl.studia.studiadzienne.restaurant

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.studia.studiadzienne.R

class ComposeActivity : ComponentActivity() {

    val viewModel by viewModels<RestaurantViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            ComposeScreen(viewModel)
        }
    }
}

@Composable
fun ComposeScreen(viewModel: RestaurantViewModel=RestaurantViewModel()) {
    // zamiana liveData na state
    val state=viewModel.liveData.observeAsState()

    Surface(modifier = Modifier.fillMaxSize()) {

        val value = state.value
        when(value){
            is RestaurantViewModel.ViewState.Loading -> ShowProgressBar()
            is RestaurantViewModel.ViewState.ShowRestaurants -> RestauranList(value)
            null -> {}
        }
    }
}

@Composable
fun ShowProgressBar() {
    TODO("Not yet implemented")
}

@Composable
fun RestauranList(state: RestaurantViewModel.ViewState.ShowRestaurants) {
    LazyColumn {
        state.restaurants.forEach {
            item {
                RestaurantRow(it)
            }
        }

    }
}

@Composable
fun RestaurantRow(restaurant: Restaurant) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.kotek),
            contentDescription = "zdjęcie restauracji",
            modifier = Modifier.size(30.dp)
        )
        Column(modifier = Modifier.padding(start = 30.dp)) {
            Text(text = restaurant.name)
            Text(text = restaurant.address)
        }
        Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
            Checkbox(checked = restaurant.open, onCheckedChange = null)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewRecordElement(){
    TextField(value = "", onValueChange = {

    })
}


//@Preview(showBackground = true)
//@Composable
//fun ComposePreview() {
//    ComposeScreen()
//}

@Preview(
    showBackground = true, device = "spec:width=411dp,height=891dp",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun RowPreview() {
    RestaurantRow(Restaurant("test", "błotna", true))
}

