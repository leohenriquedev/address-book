package com.example.crudapp.repository

import androidx.lifecycle.LiveData
import com.example.crudapp.data.db.entity.UserEntity

interface UserRepository {

    suspend fun insertUser(name: String, email: String) : Long

    suspend fun updateUser(id: Long, name: String, email: String)

    suspend fun deleteUser(id: Long)

    suspend fun deleteAllUsers()

    suspend fun getAllUsers(): LiveData<List<UserEntity>>

}