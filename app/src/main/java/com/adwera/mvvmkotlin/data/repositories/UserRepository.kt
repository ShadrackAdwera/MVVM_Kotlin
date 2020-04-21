package com.adwera.mvvmkotlin.data.repositories

import com.adwera.mvvmkotlin.data.network.MyApi
import com.adwera.mvvmkotlin.data.network.SafeApiRequest
import com.adwera.mvvmkotlin.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { MyApi().userLogin(email, password) }
    }
}
