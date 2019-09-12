package com.mvvmdemo.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvmdemo.data.db.AppDatabase
import com.mvvmdemo.data.db.entities.Quote
import com.mvvmdemo.data.network.MyApi
import com.mvvmdemo.data.network.SafeApiRequest
import com.mvvmdemo.data.prefrences.PrefrencesProvider
import com.mvvmdemo.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

private const val MINIMUM_INTERVAL = 6

class QuotesRepositories(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PrefrencesProvider
) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>> {
        return withContext(Dispatchers.IO) {

            fetchQuotes()
            db.getQuotedeo().getQuotes()

        }
    }

    private suspend fun fetchQuotes() {

        val lastsavedat = prefs.getLastSavedAt()

        if (lastsavedat == null || isFetchNeeded(LocalDateTime.parse(lastsavedat))) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(parse: LocalDateTime): Boolean {



        return true

    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
           // prefs.saveLastSaveAt(LocalDateTime.now().toString())
            db.getQuotedeo().saveAllQuotes(quotes)
        }
    }


}
