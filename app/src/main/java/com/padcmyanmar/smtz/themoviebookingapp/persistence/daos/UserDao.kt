package com.padcmyanmar.smtz.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.*

@Dao
interface UserDao {
    //UserVO
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserDataVO?)

    @Query("SELECT * FROM user WHERE token = :token")
    fun getUser(token: String): UserDataVO?

    @Query("SELECT * FROM user")
    fun getToken(): UserDataVO?

    @Query("DELETE FROM user")
    fun deleteUser()

}