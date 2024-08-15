package com.kafka.githubbasic.presentation.userdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kafka.githubbasic.R
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

            binding.tvRepoLanguage.text =
                itemView.context.getString(
                    R.string.repo_language,
                    userRepoModel.language ?: "Unknown"
                )

            binding.tvStarsNumber.text =
                itemView.context.getString(R.string.repo_stars, userRepoModel.starCount.toString())

            userRepoModel.description?.run {
                binding.tvDescription.text =
                    itemView.context.getString(R.string.repo_desc, this)
            } ?: {
                binding.tvDescription.visibility = View.GONE
            }
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