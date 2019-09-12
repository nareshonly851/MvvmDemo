package com.mvvmdemo

import android.app.Application
import com.mvvmdemo.data.db.AppDatabase
import com.mvvmdemo.data.network.MyApi
import com.mvvmdemo.data.network.NetWorkConnectionInterceptor
import com.mvvmdemo.data.prefrences.PrefrencesProvider
import com.mvvmdemo.data.repositories.QuotesRepositories
import com.mvvmdemo.data.repositories.UserRepository
import com.mvvmdemo.ui.auth.AuthViewModelFactory
import com.mvvmdemo.ui.home.profile.ProfileViewModelFactory
import com.mvvmdemo.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {


    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))

        bind() from singleton { NetWorkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { PrefrencesProvider(instance()) }
        bind() from singleton { QuotesRepositories(instance(), instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }

    }

}