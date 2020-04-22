package com.adwera.mvvmkotlin.data.repositories

import com.adwera.mvvmkotlin.data.database.AppDatabase
import com.adwera.mvvmkotlin.data.database.entities.User
import com.adwera.mvvmkotlin.data.network.MyApi
import com.adwera.mvvmkotlin.data.network.SafeApiRequest
import com.adwera.mvvmkotlin.data.network.responses.AuthResponse

class UserRepository(private val myApi: MyApi,
                     private val appDatabase: AppDatabase

) : SafeApiRequest() {
    //api user login
    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { myApi.userLogin(email, password) }
    }

    //api user signup
    suspend fun userSignUp(name:String, email:String, password:String) : AuthResponse {
        return apiRequest { myApi.userSignUp(name, email, password)}
    }

    //save user to local database
    suspend fun saveUser(user: User) = appDatabase.getUserDao().upsert(user = user)

    fun getUser() = appDatabase.getUserDao().getUser()


}
