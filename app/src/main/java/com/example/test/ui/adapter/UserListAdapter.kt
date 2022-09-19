package com.example.test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.R
import com.example.test.databinding.UserListBinding
import com.example.test.ui.MainActivity
import com.example.test.ui.model.Data


class UserListAdapter(var activity: MainActivity) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    var userdataList = ArrayList<Data>()

    class UserViewHolder(var binding: UserListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Data, activity: MainActivity) {
            binding.tvFirstName.text=item.firstName
            binding.tvLastName.text=item.lastName
            binding.tvEmail.text=item.email

            Glide.with(activity).load(item.avatar).into(binding.ivimg)

        }


    }

    fun setUserList(userList: ArrayList<Data>) {
        userdataList = userList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: UserListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_list,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.onBind(userdataList[position], activity)

    }

    override fun getItemCount(): Int {
        return userdataList.size

    }
}