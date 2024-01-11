package pl.studia.studiadzienne.properCodeStrcture.repository

import javax.inject.Inject

class LoginRepository @Inject constructor():ILoginRepository {

   override fun isLogged()=true
}