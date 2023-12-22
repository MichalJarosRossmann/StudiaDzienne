package pl.studia.studiadzienne.restaurant.db.entities


import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class TestEntityRealm :RealmObject {
    @PrimaryKey
    var id:Int=0
    var name:String=""
    var surname:String=""

}