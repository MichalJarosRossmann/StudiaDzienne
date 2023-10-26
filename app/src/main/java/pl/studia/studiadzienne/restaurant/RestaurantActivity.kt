package pl.studia.studiadzienne.restaurant

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.LinearLayoutManager
import pl.studia.studiadzienne.FirstViewModel
import pl.studia.studiadzienne.databinding.ActivityRestaurantBinding
import pl.studia.studiadzienne.databinding.ActivityStartBinding

class RestaurantActivity : AppCompatActivity() {


    val binding by lazy { ActivityRestaurantBinding.inflate(layoutInflater) }

    val viewModel by viewModels<RestaurantViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val restaurantAdapter = RestaurantAdapter()
        //LinearLayoutManager informacja jak ma się rysować recyclerView
        binding.recycerView.layoutManager=LinearLayoutManager(this)
        binding.recycerView.adapter= restaurantAdapter

        viewModel.liveData.observe(this, Observer {
            //zabezpieczenie na wszystkie view state
            when(it){
                is RestaurantViewModel.ViewState.Loading -> TODO()
                is RestaurantViewModel.ViewState.ShowRestaurants -> {

                    //Ustawienie elementów na liście
                    restaurantAdapter.restaurants.clear()
                    restaurantAdapter.restaurants.addAll(it.restaurants)
                    //informuje adapter że lista się zmieniła
                    restaurantAdapter.notifyDataSetChanged()
                }
            }

        })

        viewModel.loadRestaurants()
    }


}