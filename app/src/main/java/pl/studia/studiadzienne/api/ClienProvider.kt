package pl.studia.studiadzienne.api

import io.realm.kotlin.Configuration
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ClienProvider {

    //Dodanie klienta obsługującego komunikacje sieciową


    //Metoda tworzy klienta retrofit
    fun getRetrofitClient(): Retrofit {
        val retrofit = Retrofit.Builder()
            //potrzeba wykonywać zapytania https
            .baseUrl("https://demo0982476.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }


    //Tworzenie interface do komunikacji sieciowej
    fun getRestaurantApi():RestaurantApi{
        return getRetrofitClient().create(RestaurantApi::class.java)
    }
}