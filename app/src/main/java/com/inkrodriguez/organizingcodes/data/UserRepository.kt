package com.inkrodriguez.organizingcodes.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.inkrodriguez.organizingcodes.model.User
import kotlinx.coroutines.tasks.await

class UserRepository {

    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("users")

    suspend fun checkUsernameExists(user: User): Boolean {
        try {
            val query = collection.whereEqualTo("username", user.username)
            val result = query.get().await()

            if(!result.isEmpty) {
                return true
            }
        } catch (e: Exception) {
            Log.e("ERRO", e.message.toString())
        }
        return false
    }

    suspend fun checkRegisterUser(user: User): Boolean {
        try {
            val query = collection
                .whereEqualTo("username", user.username)
                .whereEqualTo("password", user.password)

            val result = query.get().await()

            if(!result.isEmpty) {
                return true
            }
        } catch (e: Exception) {
            Log.e("ERRO", e.message.toString())
        }
        return false
    }

    suspend fun createUser(user: User): Boolean{
        try {
            val query = collection.add(user)
            val result = query.await()

            if(result != null){
                return true
            }
        } catch (e: Exception){ Log.e("ERRO", e.message.toString()) }
        return false
    }

}
