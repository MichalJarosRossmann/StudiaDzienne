package pl.studia.studiadzienne.restaurant

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.studia.studiadzienne.api.ClienProvider
import pl.studia.studiadzienne.api.RestaurantHelloWorldResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantViewModel : ViewModel() {

    val restaurantProvider = RestaurantProvider()

    //zabezpieczenie użycia live data
    private val _liveData = MutableLiveData<ViewState>()
    val liveData: LiveData<ViewState> = _liveData

    private val restauranList = mutableListOf<Restaurant>()

    init {
        loadRestaurants()

        getHelloWorld()
    }


    //pobieranie asynchroniczne bez coroutines
    fun getHelloWorld(){
        ClienProvider().getRestaurantApi().getHelloWorld().enqueue(object :Callback<RestaurantHelloWorldResponse>{
            override fun onResponse(call: Call<RestaurantHelloWorldResponse>, response: Response<RestaurantHelloWorldResponse>) {
                Log.i("getHelloWorld","${response.body()?.msg}")
            }

            override fun onFailure(call: Call<RestaurantHelloWorldResponse>, t: Throwable) {
                Log.e("getHelloWorld","${t.message}")
            }
        })

        ClienProvider().getRestaurantApi().getRestaurantList().enqueue(object :Callback<List<Restaurant>>{
            override fun onResponse(call: Call<List<Restaurant>>, response: Response<List<Restaurant>>) {
                Log.i("getHelloWorld","${response.body()}")
            }

            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                Log.e("getHelloWorld","${t.message}")
            }

        })
    }

    fun loadRestaurants() {
        _liveData.postValue(ViewState.ShowRestaurants(restauranList))
    }

    fun addRestaurant(it: Restaurant) {
        viewModelScope.launch {
            _liveData.postValue(ViewState.Loading())
            restauranList.add(it)
            delay(2000)
            loadRestaurants()
        }

    }

    sealed class ViewState {
        class Loading : ViewState()
        class ShowRestaurants(val restaurants: List<Restaurant>) : ViewState()
    }


}