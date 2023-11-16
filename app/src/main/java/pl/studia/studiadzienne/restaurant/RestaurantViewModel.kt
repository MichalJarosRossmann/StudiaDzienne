package pl.studia.studiadzienne.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RestaurantViewModel: ViewModel() {

    val restaurantProvider=RestaurantProvider()

    //zabezpieczenie użycia live data
    private val _liveData = MutableLiveData<ViewState>()
    val liveData : LiveData<ViewState> = _liveData

    init {
        loadRestaurants()
    }
    fun loadRestaurants() {
        _liveData.postValue(ViewState.ShowRestaurants(restaurantProvider.getRestaurants()))
    }

    sealed class ViewState{
        class Loading :ViewState()
        class ShowRestaurants(val restaurants: List<Restaurant>):ViewState()
    }


}