package com.adwera.mvvmkotlin.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adwera.mvvmkotlin.data.repositories.QuotesRepository

@Suppress("UNCHECKED_CAST")
class QuotesModelFactory(private val quotesRepository: QuotesRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(quotesRepository) as T
    }
}
