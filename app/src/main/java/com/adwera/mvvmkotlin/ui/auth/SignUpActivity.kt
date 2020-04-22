package com.adwera.mvvmkotlin.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.adwera.mvvmkotlin.R
import com.adwera.mvvmkotlin.data.database.AppDatabase
import com.adwera.mvvmkotlin.data.database.entities.User
import com.adwera.mvvmkotlin.data.network.MyApi
import com.adwera.mvvmkotlin.data.network.NetworkConnectorInterceptor
import com.adwera.mvvmkotlin.data.repositories.UserRepository
import com.adwera.mvvmkotlin.databinding.ActivitySignUpBinding
import com.adwera.mvvmkotlin.ui.home.MainActivity
import com.adwera.mvvmkotlin.utils.show
import com.adwera.mvvmkotlin.utils.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkConnectorInterceptor = NetworkConnectorInterceptor(this)
        val api = MyApi(networkConnectorInterceptor)
        val db = AppDatabase(this)
        val repo = UserRepository(myApi = api, appDatabase = db)
        val factory = AuthViewModelFactory(repo)

        val binding : ActivitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }

        })

    }

    override fun onStarted() {
        progress_login.show()
    }

    override fun onSuccess(user: User) {
        progress_login.show()
        root_layout_sign_up.snackbar("Welcome ${user.name}")
    }

    override fun onFailure(message: String) {
        progress_login.show()
        root_layout_sign_up.snackbar(message = message)
    }
}
