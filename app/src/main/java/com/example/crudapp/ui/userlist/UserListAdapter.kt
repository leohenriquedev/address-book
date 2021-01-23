package com.example.crudapp.ui.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapp.R
import com.example.crudapp.data.db.entity.UserEntity
import kotlinx.android.synthetic.main.user_item.view.*

class UserListAdapter(
    private val users : List<UserEntity>
) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    var onItemClick: ((entity: UserEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)

        return UserListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bindView(users[position])
    }

    override fun getItemCount() = users.size

    inner class UserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewUserName: TextView = itemView.text_user_name
        private val textViewUserEmail: TextView = itemView.text_user_email

        fun bindView(user: UserEntity) {
            textViewUserName.text = user.name
            textViewUserEmail.text = user.email

            itemView.setOnClickListener {
                onItemClick?.invoke(user)
            }

        }

    }
}