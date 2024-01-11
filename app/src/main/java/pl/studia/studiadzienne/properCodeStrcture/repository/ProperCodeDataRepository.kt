package pl.studia.studiadzienne.properCodeStrcture.repository

import com.google.gson.Gson
import pl.studia.studiadzienne.properCodeStrcture.model.ProperCodeExampleModel
import javax.inject.Inject

class ProperCodeDataRepository @Inject constructor(val loginRepository: ILoginRepository) {

    fun getNumbers():List<ProperCodeExampleModel>{
        return listOf(
            ProperCodeExampleModel(1),
            ProperCodeExampleModel(2),
            ProperCodeExampleModel(3),
            ProperCodeExampleModel(4),
            ProperCodeExampleModel(5),
        )
    }
}