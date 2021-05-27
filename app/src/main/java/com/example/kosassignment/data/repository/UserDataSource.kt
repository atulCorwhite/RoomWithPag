package com.example.kosassignment.data.repository

import android.content.Context
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.kosassignment.data.modal.User
import com.example.kosassignment.data.modal.UserDataResponse
import com.example.kosassignment.data.room.AppDataBase
import com.example.kosassignment.data.room.UserDao

import com.example.kosassignment.data.services.ApiServiceBuilder
import com.example.navia.data.remote.services.APICallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataSource(private val context: Context) : PageKeyedDataSource<Int, User>() {

    private val userDao: UserDao = AppDataBase.getDatabase(context).userDao()
    var responseUserItems: List<User>? = null

    var service = ApiServiceBuilder.buildService(APICallBack::class.java)
    fun getAllEmployeesLiveData(): List<User> {
        return userDao.getAll()
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, User>) {
        responseUserItems=getAllEmployeesLiveData()
        getUserData(callback)




        //   service = ApiServiceBuilder.buildService(APICallBack::class.java)
        /*   val call = service.getUsers(FIRST_PAGE)

           val enqueue: Any = call.enqueue(object : Callback<UserDataResponse> {
               override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
               }

               override fun onResponse(call: Call<UserDataResponse>, response: Response<UserDataResponse>) {
                   if (response.isSuccessful) {

                       val apiResponse = response.body()!!
                        responseItems = apiResponse.users


                       Log.e("sdsd",getAllEmployeesLiveData().toString())



                       responseItems?.let {
                           callback.onResult(responseItems!!, null, FIRST_PAGE + 1)
                       }
                   }

               }

           })*/

    }

    private fun getUserData(callback: LoadInitialCallback<Int, User>) {
        if (responseUserItems?.size!! > 1) {
            responseUserItems?.let {
                callback.onResult(responseUserItems!!, null, FIRST_PAGE + 1)
            }
        } else {
            val call = service.getUsers(FIRST_PAGE)
            val enqueue: Any = call.enqueue(object : Callback<UserDataResponse> {
                override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                }

                override fun onResponse(call: Call<UserDataResponse>, response: Response<UserDataResponse>) {
                    if (response.isSuccessful) {
                        val apiResponse = response.body()!!
                        var responseItems = apiResponse.users
                        if (responseItems != null) {
                            userDao.insertAll(responseItems)
                        }

                        Log.e("sdsd",getAllEmployeesLiveData().toString())

                        responseItems?.let {
                            callback.onResult(responseItems!!, null, FIRST_PAGE + 1)
                        }
                    }

                }

            })
        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {


       // responseItems?.let { userDao.insertAll(it) }

        //  getUserData(callback)

        // service = ApiServiceBuilder.buildService(APICallBack::class.java)
        val call = service.getUsers(params.key)

        call.enqueue(object : Callback<UserDataResponse> {
            override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<UserDataResponse>, response: Response<UserDataResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                  var  responseItems = apiResponse.users
                    if (responseItems != null) {
                        userDao.insertAll(responseItems)
                    }

                   // responseUserItems = getAllEmployeesLiveData()

                    val key = params.key + 1

                    responseItems?.let {
                        callback.onResult(responseItems!!, key)
                    }
                }

            }

        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {

     //   responseItems?.let { userDao.insertAll(it) }

        //  getUserData(callback)
        val call = service.getUsers(params.key)
        call.enqueue(object : Callback<UserDataResponse> {
            override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
            }
            override fun onResponse(call: Call<UserDataResponse>, response: Response<UserDataResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                     var responseItems = apiResponse.users
                    if (responseItems != null) {
                        userDao.insertAll(responseItems)
                    }

                   // responseItems = getAllEmployeesLiveData()

                    val key = if (params.key > 1) params.key - 1 else 0

                    responseItems?.let {
                        callback.onResult(responseItems!!, key)
                    }
                }

            }

        })

    }

    companion object {
        const val PAGE_SIZE = 6
        const val FIRST_PAGE = 1

    }
}