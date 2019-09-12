package com.mvvmdemo.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.mvvmdemo.util.NoInternetConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class NetWorkConnectionInterceptor(context: Context) : Interceptor {

    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isInternetAvailable()) {

            throw NoInternetConnectionException("Make sure you have an active data connection")

        }

        return chain.proceed(chain.request())

    }

    private fun isInternetAvailable(): Boolean {
        val connectivitymanager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivitymanager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }

}