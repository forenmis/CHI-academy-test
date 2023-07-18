package com.example.chiacademytest.screens.users

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chiacademytest.databinding.ItemUserBinding
import com.example.chiacademytest.entity.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.VH>() {

    var onStudentChange: ((Long, Boolean) -> Unit)? = null
    var onUserClick: ((Long) -> Unit)? = null

    private var items = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val user = items[position]
        holder.feelUserItem(user)
        with(holder.binding) {
            switchIsStudent.setOnCheckedChangeListener { _, isChecked ->
                onStudentChange?.invoke(user.id, isChecked)
            }
            root.setOnClickListener { onUserClick?.invoke(user.id) }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newList: List<User>) {
        items = newList
        notifyDataSetChanged()
    }

    class VH(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun feelUserItem(user: User) {
            with(binding) {
                tvAge.text = user.age.toString()
                tvName.text = user.name
                switchIsStudent.isChecked = user.isStudent
            }
        }
    }
}