package com.kafka.githubbasic.presentation.userdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kafka.githubbasic.databinding.ViewtypeUserRepoBinding
import com.kafka.githubbasic.presentation.userdetail.model.UserRepoFragmentModel

class UserRepoAdapter(
    val onClickListener: (String) -> Unit
) : ListAdapter<UserRepoFragmentModel, UserRepoAdapter.UserRepoViewHolder>(object :
    DiffUtil.ItemCallback<UserRepoFragmentModel>() {
    override fun areItemsTheSame(
        oldItem: UserRepoFragmentModel, newItem: UserRepoFragmentModel
    ): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(
        oldItem: UserRepoFragmentModel, newItem: UserRepoFragmentModel
    ): Boolean {
        return oldItem == newItem
    }
}) {

    inner class UserRepoViewHolder(private val binding: ViewtypeUserRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(userRepoModel: UserRepoFragmentModel) {
            binding.llRecyclerViewItem.setOnClickListener {
                onClickListener.invoke(userRepoModel.url)
            }
            binding.tvRepoName.text = userRepoModel.name
            if (userRepoModel.language.isNotEmpty()) {
                binding.tvRepoLanguage.text = "Language: ${userRepoModel.language}"
            }
            binding.tvStarsNumber.text = "Stars: ${userRepoModel.starCount}"
            binding.tvDescription.text = "Desc: ${userRepoModel.description}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder {
        return UserRepoViewHolder(
            ViewtypeUserRepoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        holder.setData(getItem(position))
    }
}