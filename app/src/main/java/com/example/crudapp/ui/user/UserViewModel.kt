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

    fun addUser(name: String, email: String) = viewModelScope.launch {
        try {
            val id = repository.insertUser(name, email)
            if(id > 0) {
                _userStateEventData.value = UserState.Inserted
                _messageEventData.value = R.string.user_added_success
            }
        } catch (ex: Exception) {
            _messageEventData.value = R.string.user_added_error
            Log.e(TAG, ex.toString())
        }
    }

    sealed class UserState {
        object Inserted : UserState()
    }

    companion object {
        private val TAG = UserViewModel::class.java.simpleName
    }

}