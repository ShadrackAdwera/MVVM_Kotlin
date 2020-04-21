package com.adwera.mvvmkotlin.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.adwera.mvvmkotlin.data.repositories.UserRepository
import com.adwera.mvvmkotlin.utils.Coroutines

class AuthViewModel : ViewModel() {
    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginClicked(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid Email/Password")
            return
        }

        Coroutines.main {
            val loginResponse = UserRepository().userLogin(email!!, password!!)
            if (loginResponse.isSuccessful) {
                authListener?.onSuccess(loginResponse.body()?.user!!)
            } else {
                authListener?.onFailure("Error Code: ${loginResponse.code()}")
            }
        }
    }
}