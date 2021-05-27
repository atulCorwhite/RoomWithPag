package com.example.kosassignment.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kosassignment.data.modal.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(users: List<User>)

    @Query("SELECT * FROM user_data_table")
    fun getAll(): List<User>

}