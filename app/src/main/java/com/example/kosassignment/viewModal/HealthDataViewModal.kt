package com.example.navia.viewModal

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.kosassignment.data.modal.User
import com.example.kosassignment.data.modal.UserDataResponse
import com.example.kosassignment.data.repository.UserDataSource
import com.example.kosassignment.data.repository.UserDataSourceFactory


class HealthDataViewModal(private val context: Context): ViewModel() {


    val userPagedList : LiveData<PagedList<User>>
    private val liveDataSource : LiveData<UserDataSource>

    init {
        val itemDataSourceFactory = UserDataSourceFactory(context)

        liveDataSource = itemDataSourceFactory.userLiveDataSource

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(UserDataSource.PAGE_SIZE)
            .build()

        userPagedList = LivePagedListBuilder(itemDataSourceFactory,config)
            .build()

    }

}