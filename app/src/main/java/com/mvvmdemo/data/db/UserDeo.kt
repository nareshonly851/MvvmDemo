package com.mvvmdemo.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvvmdemo.data.db.entities.CURRENT_USER_ID
import com.mvvmdemo.data.db.entities.User

@Dao
interface UserDeo {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upset(user: User): Long

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getuser(): LiveData<User>


}