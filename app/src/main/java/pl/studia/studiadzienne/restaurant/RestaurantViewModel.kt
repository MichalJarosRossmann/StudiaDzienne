package pl.studia.studiadzienne.restaurant

import android.os.AsyncTask
import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
//        getHelloWorld()
//        asycnOld()
        asyncNew()
        getRestauranAsync()
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


    fun asycnOld(){
        Thread(Runnable{
            Log.i("Thread","Thread start")
            Thread.sleep(1000)
            Log.i("Thread","Thread end")

            _liveData.postValue(ViewState.Loading())
        }).start()

        object:AsyncTask<String,String,String>(){

            //background thread
            override fun doInBackground(vararg p0: String?): String {
                Log.i("Thread","Thread start")
                Thread.sleep(1000)
                Log.i("Thread","Thread end")
                return "test"
            }

            //main thread
            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
            }
        }

        Handler().postDelayed({
//main thread after 1000ms
        },1000)
    }

    fun asyncNew(){
        CoroutineScope(Dispatchers.Main).launch {
            Log.i("CoroutineScope","Thread start")
            val asycn1=async { onBackground() }
           val async2= async {onBackground2()}

            awaitAll(asycn1,async2)
            Log.i("CoroutineScope","Thread end")
        }

    }

    fun getRestauranAsync(){
        CoroutineScope(Dispatchers.Main).launch {
        val restaurantListSuspend = try {
            ClienProvider().getRestaurantApi().getRestaurantListSuspend()
        } catch (e: Exception) {
            e.printStackTrace()
        }

            Log.i("getRestauranAsync","$restaurantListSuspend")
        }
    }

    suspend fun onBackground()= withContext(Dispatchers.IO){
        Log.i("CoroutineScope","onBackground start")
        delay(10000)
//        Thread.sleep(10000)
        Log.i("CoroutineScope","onBackground end")
    }

    suspend fun onBackground2()= withContext(Dispatchers.Default){
        Log.i("CoroutineScope","onBackground2 start")
        delay(1000)
//        Thread.sleep(10000)
        Log.i("CoroutineScope","onBackground2 end")
    }


    sealed class ViewState {
        class Loading : ViewState()
        class ShowRestaurants(val restaurants: List<Restaurant>) : ViewState()
    }


}