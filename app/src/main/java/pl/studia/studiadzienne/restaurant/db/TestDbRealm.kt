package pl.studia.studiadzienne.restaurant.db

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import pl.studia.studiadzienne.restaurant.db.entities.TestEntityRealm

class TestDbRealm {

    fun getDb(): Realm {
        val config = RealmConfiguration.create(schema = setOf(TestEntityRealm::class))
        val realm: Realm = Realm.open(config)

        return realm
    }

    fun getField():TestEntityRealm?{
       return getDb().query(TestEntityRealm::class).first().find()
    }

    fun saveField(entity:TestEntityRealm){
        getDb().writeBlocking {
            this.copyToRealm(entity)
        }
    }
}