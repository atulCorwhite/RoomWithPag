package com.example.kosassignment.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.kosassignment.data.modal.User


class UserDataSourceFactory(private val context: Context): DataSource.Factory<Int, User>() {
    val userLiveDataSource = MutableLiveData<UserDataSource>()
    override fun create(): DataSource<Int, User> {
        val userDataSource = UserDataSource(context)
        userLiveDataSource.postValue(userDataSource)
        return userDataSource

    }
}