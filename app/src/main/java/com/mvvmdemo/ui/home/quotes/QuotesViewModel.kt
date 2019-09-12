package com.mvvmdemo.ui.home.quotes


import androidx.lifecycle.ViewModel
import com.mvvmdemo.data.repositories.QuotesRepositories
import com.mvvmdemo.util.lazyDeferred


class QuotesViewModel(repository: QuotesRepositories) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }


}
