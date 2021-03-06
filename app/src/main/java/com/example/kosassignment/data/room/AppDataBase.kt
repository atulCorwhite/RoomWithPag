package com.example.kosassignment.data.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kosassignment.data.modal.User

@Database(entities = [User::class], version = 1, exportSchema = false)

abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context) = INSTANCE ?: kotlin.run {
            Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "USER_DATABASE")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
        }
    }
}