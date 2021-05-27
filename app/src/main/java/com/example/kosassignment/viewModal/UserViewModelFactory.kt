package com.example.kosassignment.viewModal

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.navia.viewModal.HealthDataViewModal

class UserViewModelFactory(private val context: Context): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HealthDataViewModal(context)as T
    }
}