package com.adwera.mvvmkotlin.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.adwera.mvvmkotlin.R
import com.adwera.mvvmkotlin.data.database.AppDatabase
import com.adwera.mvvmkotlin.data.network.MyApi
import com.adwera.mvvmkotlin.data.network.NetworkConnectorInterceptor
import com.adwera.mvvmkotlin.data.repositories.UserRepository
import com.adwera.mvvmkotlin.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val networkConnectorInterceptor = NetworkConnectorInterceptor(this.requireContext())
        val api = MyApi(networkConnectorInterceptor)
        val db = AppDatabase(this.requireContext())
        val repo = UserRepository(myApi = api, appDatabase = db)
        val factory = ProfileModelFactory(repo)

        val binding: ProfileFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}
