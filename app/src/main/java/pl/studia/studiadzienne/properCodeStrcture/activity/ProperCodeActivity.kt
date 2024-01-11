package pl.studia.studiadzienne.properCodeStrcture.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import pl.studia.studiadzienne.properCodeStrcture.viewModels.ProperCodeViewModel

@AndroidEntryPoint
class ProperCodeActivity: AppCompatActivity() {

    val viewModel by viewModels<ProperCodeViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getData()


    }
}