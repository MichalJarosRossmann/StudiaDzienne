package pl.studia.studiadzienne.properCodeStrcture.modlules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import pl.studia.studiadzienne.properCodeStrcture.repository.ILoginRepository
import pl.studia.studiadzienne.properCodeStrcture.repository.LoginRepository
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class ProperCodeMopdules {

    @Provides
    fun getLoginRepository():ILoginRepository=LoginRepository()
}