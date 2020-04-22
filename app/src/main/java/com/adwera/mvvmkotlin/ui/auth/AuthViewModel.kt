package com.adwera.mvvmkotlin.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.adwera.mvvmkotlin.data.repositories.UserRepository
import com.adwera.mvvmkotlin.utils.ApiException
import com.adwera.mvvmkotlin.utils.Coroutines
import com.adwera.mvvmkotlin.utils.NoInternetException

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null


    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

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
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(loginResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.localizedMessage!!)
            } catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }

        }
    }
    fun onSignUpClicked(view: View) {
        authListener?.onStarted()
        if (name.isNullOrEmpty()||email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Fill all fields Nigga!")
            return
        }
        if(password!=passwordConfirm){
            authListener?.onFailure("Passwords dont match fool!!!")
            return
        }

        Coroutines.main {
            try {
                val loginResponse = repository.userSignUp(name!!,email!!, password!!)
                loginResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(loginResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.localizedMessage!!)
            } catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }

        }
    }
    fun onSignUp(view:View){
        Intent(view.context, SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
}