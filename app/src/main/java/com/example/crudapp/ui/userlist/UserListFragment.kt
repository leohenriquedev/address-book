package com.example.crudapp.ui.userlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.crudapp.R
import com.example.crudapp.data.db.entity.UserEntity
import kotlinx.android.synthetic.main.user_list_fragment.*

class UserListFragment : Fragment(R.layout.user_list_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userListAdapter = UserListAdapter(
            listOf(
                UserEntity(1, "Leonardo Henrique", "heenriqueleo@gmail.com"),
                UserEntity(2, "Rebeca Mendes", "rebecamendes@gmail.com")
            )
        )

        recycler_users.run { 
            setHasFixedSize(true)
            adapter = userListAdapter
        }
    }


}