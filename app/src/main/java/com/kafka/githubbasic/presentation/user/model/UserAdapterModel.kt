package com.kafka.githubbasic.presentation.user.model

import com.kafka.githubbasic.domain.user.model.UserListModel

data class UserAdapterModel(
    val id: Int, val userName: String, val avatarUrl: String
)

fun UserListModel.toUserAdapterModel() = UserAdapterModel(
    id, name, avatarUrl
)
