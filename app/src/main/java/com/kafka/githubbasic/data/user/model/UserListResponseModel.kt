package com.kafka.githubbasic.data.user.model

data class UserListResponseModel(
    val login: String,
    val id: Int,
    val avatar_url: String
)