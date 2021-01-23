package com.example.crudapp.ui.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudapp.R
import com.example.crudapp.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _userStateEventData = MutableLiveData<UserState>()
    val userStateEventData: LiveData<UserState>
        get() = _userStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addOrUpdateUser(name: String, email: String, id: Long = 0) {
        if (id > 0) {
            updateUser(id, name, email)
        } else {
            insertUser(name, email)
        }
    }

    private fun insertUser(name: String, email: String) = viewModelScope.launch {
        try {
            val id = repository.insertUser(name, email)
            if(id > 0) {
                _userStateEventData.value = UserState.Updated
                _messageEventData.value = R.string.user_added_success
            }
        } catch (ex: Exception) {
            _messageEventData.value = R.string.user_added_error
            Log.e(TAG, ex.toString())
        }
    }

    private fun updateUser(id: Long, name: String, email: String) = viewModelScope.launch {
        try {
            repository.updateUser(id, name, email)
            _userStateEventData.value = UserState.Inserted
            _messageEventData.value = R.string.user_updated_success

        } catch (ex: Exception) {
            _messageEventData.value = R.string.user_updated_error
            Log.e(TAG, ex.toString())
        }
    }


    fun removeUser(id: Long) = viewModelScope.launch {
        try {
            if(id > 0) {
                repository.deleteUser(id)
                _userStateEventData.value = UserState.Deleted
                _messageEventData.value = R.string.user_deleted_success
            }
        } catch (ex: Exception) {
            _messageEventData.value = R.string.user_deleted_error
            Log.e(TAG, ex.toString())
        }
    }

    sealed class UserState {
        object Inserted : UserState()
        object Updated: UserState()
        object Deleted: UserState()
    }

    companion object {
        private val TAG = UserViewModel::class.java.simpleName
    }

}