package com.example.crudapp.ui.userlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.crudapp.R
import com.example.crudapp.data.db.AppDatabase
import com.example.crudapp.data.db.dao.UserDAO
import com.example.crudapp.extension.navigateWithAnimations
import com.example.crudapp.repository.DatabaseDataSource
import com.example.crudapp.repository.UserRepository
import kotlinx.android.synthetic.main.user_list_fragment.*

class UserListFragment : Fragment(R.layout.user_list_fragment) {

    private val viewModel: UserListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val userDAO: UserDAO =
                    AppDatabase.getInstance(requireContext()).userDAO

                val repository: UserRepository = DatabaseDataSource(userDAO)
                return UserListViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Adapter of User's List
        observeViewModelEvents()

        // Action of Floating Button
        configureViewListeners()
    }

    private fun observeViewModelEvents() {
        viewModel.allUsersEvent.observe(viewLifecycleOwner) { allUsers ->
            val userListAdapter = UserListAdapter(allUsers)

            with(recycler_users) {
                setHasFixedSize(true)
                adapter = userListAdapter
            }
        }

    }

    private fun configureViewListeners() {
        fabAddUser.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.userFragment)
        }
    }

}