package com.mvvmdemo.data.repositories


import com.mvvmdemo.data.db.AppDatabase
import com.mvvmdemo.data.db.entities.User
import com.mvvmdemo.data.network.MyApi
import com.mvvmdemo.data.network.SafeApiRequest
import com.mvvmdemo.data.network.responses.AuthResponse


class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userSignup(name: String, email: String, password: String): AuthResponse {
        return apiRequest { api.userSignup(name, email, password) }
    }

    suspend fun userLogin( email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin( email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserdao().upset(user)

    fun getUser() = db.getUserdao().getuser()



}




