package com.adwera.mvvmkotlin.ui.home.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adwera.mvvmkotlin.R
import com.adwera.mvvmkotlin.data.database.AppDatabase
import com.adwera.mvvmkotlin.data.database.entities.Quote
import com.adwera.mvvmkotlin.data.network.MyApi
import com.adwera.mvvmkotlin.data.network.NetworkConnectorInterceptor
import com.adwera.mvvmkotlin.data.preferences.PreferenceProvider
import com.adwera.mvvmkotlin.data.repositories.QuotesRepository
import com.adwera.mvvmkotlin.utils.Coroutines
import com.adwera.mvvmkotlin.utils.hide
import com.adwera.mvvmkotlin.utils.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*

class QuotesFragment : Fragment() {

    private lateinit var viewModel: QuotesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val networkConnectorInterceptor = NetworkConnectorInterceptor(this.requireContext())
        val api = MyApi(networkConnectorInterceptor)
        val db = AppDatabase(this.requireContext())
        val prefs = PreferenceProvider(this.requireContext())
        val repo = QuotesRepository(api, db, prefs)
        val factory = QuotesModelFactory(repo)
        viewModel = ViewModelProvider(this, factory).get(QuotesViewModel::class.java)
        // TODO: Use the ViewModel
        bindUi()
    }

    private fun bindUi()  = Coroutines.main {
        progress_quotes.show()
        viewModel.quotes.await().observe(viewLifecycleOwner, Observer {
            progress_quotes.hide()
            initRecyclerView(it.toQuoteItem())
        })
    }

    private fun List<Quote>.toQuoteItem() : List<QuoteItem>{
        return this.map {
            QuoteItem(it)
        }
    }

    private fun initRecyclerView(quoteItem: List<QuoteItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }

    }

}
