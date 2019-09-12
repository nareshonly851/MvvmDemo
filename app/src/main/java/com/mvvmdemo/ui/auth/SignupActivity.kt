package com.mvvmdemo.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mvvmdemo.R
import com.mvvmdemo.data.db.entities.User

import com.mvvmdemo.databinding.ActivitySignupBinding
import com.mvvmdemo.ui.home.HomeActivity
import com.mvvmdemo.util.hide
import com.mvvmdemo.util.show
import com.mvvmdemo.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {


    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding: ActivitySignupBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLogginedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }


    override fun onSuccess(user: User) {
        progress_bar.hide()
        toast("${user.name} is logged in")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        //   cl_root_layout.snackbar(message)
        toast(message)

    }

    override fun onStarted() {
        progress_bar.show()
        toast("Login onStarted")
    }
}
