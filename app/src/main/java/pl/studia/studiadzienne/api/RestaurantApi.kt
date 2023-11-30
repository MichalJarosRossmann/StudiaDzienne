package pl.studia.studiadzienne.api

import pl.studia.studiadzienne.api.responses.RestaurantResponse
import pl.studia.studiadzienne.restaurant.Restaurant
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface RestaurantApi {

    @GET("testHelloWorld")
    fun getHelloWorld(): Call<RestaurantHelloWorldResponse>


    @GET("RestaurantList")
    fun getRestaurantList(): Call<List<Restaurant>>

    @GET("RestaurantList")
    suspend fun getRestaurantListSuspend():List<RestaurantResponse>

    @GET("RestaurantList")
    suspend fun getRestaurantListSuspend2():Response<List<Restaurant>>




}