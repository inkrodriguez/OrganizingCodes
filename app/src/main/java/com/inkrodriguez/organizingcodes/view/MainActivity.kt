package com.inkrodriguez.organizingcodes.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.inkrodriguez.organizingcodes.R
import com.inkrodriguez.organizingcodes.model.User
import com.inkrodriguez.organizingcodes.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        btnLogin.setOnClickListener {
            val username = etUsernameLogin.text.toString()
            val password = etPasswordLogin.text.toString()

            val user = User(username, password)

            GlobalScope.launch(Dispatchers.Main) {

                if (username.isEmpty()) {
                    showToast("Enter your username")
                } else if (password.isEmpty()) {
                    showToast("Enter your password")
                } else {
                    val checkAnswer = viewModel.checkRegisterUser(user)

                    if (checkAnswer) {
                        showToast("You are successfully logged in!")
                    } else {
                        showToast("Your credentials are invalid!")
                    }
                }
            }
        }


        btnRegister.setOnClickListener {

            val username = etUsernameRegister.text.toString()
            val password = etPasswordRegister.text.toString()

            val user = User(username, password)

            GlobalScope.launch(Dispatchers.Main) {

                if(username.isEmpty()){
                    showToast("Enter your username")
                } else if (password.isEmpty()){
                    showToast("Enter your password")
                } else {
                    val checkUsernameExists = viewModel.checkUsernameExists(user)

                    if(checkUsernameExists){
                        showToast("This user already exists!")
                    } else {
                        val isUserRegistered = viewModel.createUser(user)
                        if (isUserRegistered) {
                            showToast("Registration successful!")
                        } else {
                            showToast("Registration failed!")
                        }
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
