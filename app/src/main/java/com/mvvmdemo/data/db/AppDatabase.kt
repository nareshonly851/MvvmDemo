package com.mvvmdemo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mvvmdemo.data.db.entities.Quote
import com.mvvmdemo.data.db.entities.User
import java.util.concurrent.locks.Lock

@Database(entities = [User::class,Quote::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserdao(): UserDeo
    abstract fun getQuotedeo(): QuoteDeo

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it  }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "MyDatabase.db"
        ).build()
    }
}