package com.mvvmdemo.ui.home.profile

import androidx.lifecycle.ViewModel
import com.mvvmdemo.data.repositories.UserRepository

class ProfileViewModel(repository: UserRepository) : ViewModel() {

    val user = repository.getUser()


}
