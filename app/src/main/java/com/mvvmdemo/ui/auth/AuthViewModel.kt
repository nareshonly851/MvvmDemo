package com.mvvmdemo.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.mvvmdemo.data.repositories.UserRepository
import com.mvvmdemo.util.ApiException
import com.mvvmdemo.util.Coroutines
import com.mvvmdemo.util.NoInternetConnectionException

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    fun getLogginedInUser() = repository.getUser()

    fun onLoginButtonClicked(view: View) {

        authListener?.onStarted()
        if (email.isNullOrEmpty() && password.isNullOrEmpty()) {

            authListener?.onFailure("Invalide Email and Password")
            return
        }

        Coroutines.main {
            try {
                val response = repository.userLogin(email!!, password!!)
                response.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(response.message!!)

            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)


            } catch (e: NoInternetConnectionException) {
                authListener?.onFailure(e.message!!)
            }


        }


    }


    fun onSignup(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignupButtonClicked(view: View) {

        authListener?.onStarted()
        if (name.isNullOrEmpty()) {
            authListener?.onFailure("Invalides Name")
            return
        }
        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Invalides email")
            return
        }
        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Invalides password")
            return
        }

        Coroutines.main {
            try {
                val response = repository.userSignup(name!!, email!!, password!!)
                response.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(response.message!!)

            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)


            } catch (e: NoInternetConnectionException) {
                authListener?.onFailure(e.message!!)
            }


        }


    }


}