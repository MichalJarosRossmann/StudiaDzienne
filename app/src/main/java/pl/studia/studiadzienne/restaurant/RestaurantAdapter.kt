package pl.studia.studiadzienne.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import pl.studia.studiadzienne.databinding.HolderRestaurantBinding

class RestaurantAdapter : RecyclerView.Adapter<ViewHolder>() {
//lista  elementów do wyświetlenia
    val restaurants = mutableListOf<Restaurant>()

    //Tworzenie widoku do wyświetlenia na liśćie
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        HolderRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RestaurantViewHolder(HolderRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }



    //usupełnienie już utworzonego widoku
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as RestaurantViewHolder).bind(restaurants[position])
    }

    class RestaurantViewHolder(val binding: HolderRestaurantBinding) : ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant) {

            binding.resturantName.text=restaurant.name
            binding.restaurantAddress.text=restaurant.address
            binding.isOpen.isChecked=restaurant.open

        }
    }
}