package com.jaydip.mvvmdemo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jaydip.mvvmdemo.model.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("DELETE from user_tbl")
    suspend fun deleteAllUser()

    @Query("SELECT user_name,user_id,user_email,user_avatar FROM user_tbl")
    fun getAllUser(): Flow<List<User>>

}