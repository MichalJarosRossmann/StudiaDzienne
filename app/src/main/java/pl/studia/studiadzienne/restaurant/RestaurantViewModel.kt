package pl.studia.studiadzienne.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RestaurantViewModel: ViewModel() {

    val restaurantProvider=RestaurantProvider()

    //zabezpieczenie użycia live data
    private val _liveData = MutableLiveData<ViewState>()
    val liveData : LiveData<ViewState> = _liveData

    private val restauranList= mutableListOf<Restaurant>()

    init {
        loadRestaurants()
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

    sealed class ViewState{
        class Loading :ViewState()
        class ShowRestaurants(val restaurants: List<Restaurant>):ViewState()
    }


}