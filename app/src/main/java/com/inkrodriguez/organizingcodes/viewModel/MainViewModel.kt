package com.inkrodriguez.organizingcodes.viewModel

import androidx.lifecycle.ViewModel
import com.inkrodriguez.organizingcodes.data.UserRepository
import com.inkrodriguez.organizingcodes.model.User

class MainViewModel: ViewModel() {

    private val userRepository: UserRepository = UserRepository()

    suspend fun createUser(user: User): Boolean {
        return try {
            userRepository.createUser(user)
            true // Registro bem-sucedido
        } catch (e: Exception) {
            false // Registro falhou
        }
    }

    suspend fun checkRegisterUser(user: User): Boolean{
        return try {
            userRepository.checkRegisterUser(user)
        } catch (e: Exception){
            false
        }
    }

    suspend fun checkUsernameExists(user: User): Boolean {
        return try {
            userRepository.checkUsernameExists(user)
        } catch (e: Exception){
            false
        }
    }

}