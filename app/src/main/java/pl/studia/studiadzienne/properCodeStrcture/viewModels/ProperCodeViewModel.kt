package pl.studia.studiadzienne.properCodeStrcture.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.studia.studiadzienne.properCodeStrcture.repository.LoginRepository
import pl.studia.studiadzienne.properCodeStrcture.repository.ProperCodeDataRepository
import javax.inject.Inject

@HiltViewModel
class ProperCodeViewModel @Inject constructor(var repository:ProperCodeDataRepository): ViewModel() {


    fun getData(){
        repository.getNumbers().forEach {
            Log.i("ProperCodeActivity",it.number.toString())
        }
    }

}