package com.kafka.githubbasic.presentation.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kafka.githubbasic.databinding.ViewtypeUserDetailsBinding
import com.kafka.githubbasic.presentation.user.model.UserAdapterModel

class UserAdapter(
    val onClickListener: (String) -> Unit
) : ListAdapter<UserAdapterModel, UserAdapter.UsersViewHolder>(object :
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
            binding.llRecyclerViewItem.setOnClickListener {
                onClickListener.invoke(userAdapterModel.userName)
            }
            binding.tvUserName.text = userAdapterModel.userName
            Glide.with(itemView.context).load(userAdapterModel.avatarUrl).into(binding.ivUserDetail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            ViewtypeUserDetailsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    fun addList(list: List<UserAdapterModel>?) {
        val updatedList: List<UserAdapterModel> = currentList + list.orEmpty()
        submitList(updatedList)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.setData(getItem(position))
    }
}