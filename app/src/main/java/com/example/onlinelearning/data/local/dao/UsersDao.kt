package com.example.onlinelearning.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onlinelearning.data.local.entity.UserEntity

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(userEntity: UserEntity): Long

    @Query("SELECT * FROM userData WHERE username = :username")
    fun getUser(username: String): UserEntity?
}