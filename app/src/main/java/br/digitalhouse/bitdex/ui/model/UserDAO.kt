package br.digitalhouse.bitdex.ui.model

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserDAO {

    fun insertUser(user : User){
        var database = Firebase.database.getReference("users")
        database.child(user.id).setValue(user)
    }

}