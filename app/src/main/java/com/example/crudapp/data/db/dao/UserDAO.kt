package com.example.crudapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.crudapp.data.db.entity.UserEntity

@Dao
interface UserDAO {
    @Insert
    suspend fun insert(user: UserEntity): Long

    @Update
    suspend fun update(user: UserEntity)

    @Query(value = "DELETE FROM users WHERE id = :id")
    suspend fun delete(id: Long)

    @Query(value = "DELETE FROM users")
    suspend fun deleteAll()

    @Query(value = "SELECT * FROM users")
    fun getAll(): LiveData<List<UserEntity>>
}