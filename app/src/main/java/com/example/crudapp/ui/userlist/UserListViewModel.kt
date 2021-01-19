package com.example.crudapp.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudapp.data.db.entity.UserEntity
import com.example.crudapp.repository.UserRepository
import kotlinx.coroutines.launch

class UserListViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _allUsersEvent = MutableLiveData<List<UserEntity>>()
    val allUsersEvent: LiveData<List<UserEntity>>
        get() = _allUsersEvent

    fun getUsers() = viewModelScope.launch {
        _allUsersEvent.postValue(repository.getAllUsers())
    }

}