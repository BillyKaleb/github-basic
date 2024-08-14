package com.kafka.githubbasic.presentation.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kafka.githubbasic.databinding.ViewtypeUserDetailsBinding
import com.kafka.githubbasic.presentation.user.model.UserAdapterModel

class UserAdapter : ListAdapter<UserAdapterModel, UserAdapter.UsersViewHolder>(object :
    DiffUtil.ItemCallback<UserAdapterModel>() {
    override fun areItemsTheSame(
        oldItem: UserAdapterModel, newItem: UserAdapterModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UserAdapterModel, newItem: UserAdapterModel
    ): Boolean {
        return oldItem == newItem
    }
}) {

    inner class UsersViewHolder(private val binding: ViewtypeUserDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(userAdapterModel: UserAdapterModel) {
            binding.tvUserName.text = userAdapterModel.userName
            // TODO add image load by glide for image
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            ViewtypeUserDetailsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.setData(getItem(position))
    }
}