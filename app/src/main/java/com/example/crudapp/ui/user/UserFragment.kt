package com.example.crudapp.ui.user

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.crudapp.R
import com.example.crudapp.data.db.AppDatabase
import com.example.crudapp.data.db.dao.UserDAO
import com.example.crudapp.extension.hideKeyBoard
import com.example.crudapp.repository.DatabaseDataSource
import com.example.crudapp.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.user_fragment.*

class UserFragment : Fragment(R.layout.user_fragment) {

    private val viewModel: UserViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val userDAO: UserDAO =
                    AppDatabase.getInstance(requireContext()).userDAO

                val repository: UserRepository = DatabaseDataSource(userDAO)
                return UserViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvents()
        setListeners()
    }

    private fun observeEvents() {

        viewModel.userStateEventData.observe(viewLifecycleOwner) { userState ->
            when(userState) {
                is UserViewModel.UserState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                }
            }
        }

        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }

    }

    private fun clearFields() {
        input_name.text?.clear()
        input_email.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if(parentActivity is AppCompatActivity) {
            parentActivity.hideKeyBoard()
        }
    }

    private fun setListeners() {
        button_add_user.setOnClickListener {
            val name = input_name.text.toString()
            val email = input_email.text.toString()

            viewModel.addUser(name, email)
        }
    }

}