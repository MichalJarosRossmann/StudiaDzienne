package pl.studia.studiadzienne

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.studia.studiadzienne.databinding.ActivityStartBinding

class FirstActivity: AppCompatActivity() {

    val longFunction=LongFunction()

    val binding by lazy{ActivityStartBinding.inflate(layoutInflater)}

    val viewModel by viewModels<FirstViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        viewModel.liveData.observe(this, Observer {
            binding.progressBar.visibility= View.GONE
            when(it){
                is FirstViewModel.ViewState.Loading -> binding.progressBar.visibility= View.VISIBLE
                is FirstViewModel.ViewState.ShowText -> {
                    binding.textView.text=it.text
                }
            }
        })

        binding.button.setOnClickListener {
            viewModel.getSomething()
        }


    }
}