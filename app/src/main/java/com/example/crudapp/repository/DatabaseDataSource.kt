package com.example.crudapp.repository

import androidx.lifecycle.LiveData
import com.example.crudapp.data.db.dao.UserDAO
import com.example.crudapp.data.db.entity.UserEntity

class DatabaseDataSource(
    private val userDAO: UserDAO
) : UserRepository {

    override suspend fun insertUser(name: String, email: String): Long {
        val user = UserEntity(
            name = name,
            email = email
        )

        return userDAO.insert(user)
    }

    override suspend fun updateUser(id: Long, name: String, email: String) {
        val user = UserEntity(
            id = id,
            name = name,
            email = email
        )
        userDAO.update(user)
    }

    override suspend fun deleteUser(id: Long) {
        userDAO.delete(id)
    }

    override suspend fun deleteAllUsers() {
        userDAO.deleteAll()
    }

    override suspend fun getAllUsers(): LiveData<List<UserEntity>> {
        return userDAO.getAll()
    }

}