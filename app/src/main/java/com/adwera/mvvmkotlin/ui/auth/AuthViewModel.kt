package com.adwera.mvvmkotlin.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.adwera.mvvmkotlin.data.repositories.UserRepository
import com.adwera.mvvmkotlin.utils.ApiException
import com.adwera.mvvmkotlin.utils.Coroutines

class AuthViewModel(private val repository: UserRepository) : ViewModel() {
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
            try {
                val loginResponse = repository.userLogin(email!!, password!!)
                loginResponse.user?.let {
                    authListener?.onSuccess(it)
                    return@main
                }
                authListener?.onFailure(loginResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.localizedMessage!!)
            }

        }
    }
}