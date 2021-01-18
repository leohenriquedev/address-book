package com.example.crudapp.ui.userlist

import androidx.lifecycle.ViewModel
import com.example.crudapp.repository.UserRepository

class UserListViewModel(
    private val repository: UserRepository
) : ViewModel() {

    val allUsersEvent = repository.getAllUsers()


}