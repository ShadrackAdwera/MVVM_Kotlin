package com.adwera.mvvmkotlin.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.adwera.mvvmkotlin.data.repositories.QuotesRepository
import com.adwera.mvvmkotlin.utils.lazyDeferred

class QuotesViewModel(private val quotesRepository: QuotesRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    val quotes by lazyDeferred {
        quotesRepository.getQuotes()
    }
}
