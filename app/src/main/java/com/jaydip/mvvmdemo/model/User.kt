package com.jaydip.mvvmdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_tbl")
data class User (
    @PrimaryKey(autoGenerate = true)
    @SerializedName( "id")
    @ColumnInfo(name ="user_id")
    val id: Int = 0,


    @SerializedName( "name")
    @ColumnInfo(name="user_name")
    val name: String = "",

    @SerializedName( "email")
    @ColumnInfo(name="user_email")
    val email: String = "",

    @SerializedName( "avatar")
    @ColumnInfo(name="user_avatar")
    val avatar: String = ""
){
    constructor() : this(0, "", "", "")
}
