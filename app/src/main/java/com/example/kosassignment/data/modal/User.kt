package com.example.kosassignment.data.modal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "user_data_table")
data class User (
    @PrimaryKey
    @ColumnInfo
    @field:SerializedName("id")
    var id:Int,
    @ColumnInfo

    @field:SerializedName("email")
    var email:String?=null,
    @ColumnInfo

    @field:SerializedName("first_name")
    var first_name:String?=null,
    @ColumnInfo

    @field:SerializedName("last_name")
    var last_name:String?=null,
    @ColumnInfo
    @field:SerializedName("avatar")
    var avatar:String?=null
)

class UserDataResponse{
    @field:SerializedName("data")
    var users: List<User>? = null
    @field:SerializedName("page")
    var page: Int = 0
    @field:SerializedName("per_page")
    var perPage: Long = 0
    @field:SerializedName("total")
    var total: Long = 0
    @field:SerializedName("total_pages")
    var totalPages: Int = 0
}