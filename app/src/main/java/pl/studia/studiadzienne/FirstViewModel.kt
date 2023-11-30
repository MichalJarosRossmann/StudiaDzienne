package pl.studia.studiadzienne

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirstViewModel : ViewModel() {

    val longFunction = LongFunction()

    val liveData = MutableLiveData<ViewState>()



    fun getSomething() {
        CoroutineScope(Dispatchers.IO).launch {
            liveData.postValue(ViewState.Loading())
            val something = longFunction.doSomething()

            liveData.postValue(ViewState.ShowText(something))


        }
    }

    sealed class ViewState{
        class Loading :ViewState()
        class ShowText(val text:String):ViewState()
    }
}