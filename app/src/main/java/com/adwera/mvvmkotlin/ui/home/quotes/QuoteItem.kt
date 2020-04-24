package com.adwera.mvvmkotlin.ui.home.quotes

import com.adwera.mvvmkotlin.R
import com.adwera.mvvmkotlin.data.database.entities.Quote
import com.adwera.mvvmkotlin.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(private val quote: Quote) : BindableItem<ItemQuoteBinding>() {
    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }

}