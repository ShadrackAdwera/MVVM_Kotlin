package com.adwera.mvvmkotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adwera.mvvmkotlin.data.network.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun userLogin(email:String, password:String) : LiveData<String>{
        val loginResponse = MutableLiveData<String>()

        MyApi.invoke().userLogin(email, password).enqueue(object : Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                loginResponse.value = t.localizedMessage
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                //To change body of created functions use File | Settings | File Templates.
                if(response.isSuccessful && response.body()!=null){
                    loginResponse.value = response.body()?.string()
                } else {
                    loginResponse.value = response.errorBody()?.string()

                }            }

        })
        return loginResponse
    }
}