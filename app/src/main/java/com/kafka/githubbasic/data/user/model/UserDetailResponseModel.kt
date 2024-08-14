package com.kafka.githubbasic.data.user.model

data class UserDetailResponseModel(
    val login: String,
    val name: String,
    val id: Int,
    val avatar_url: String,
    val followers: Int,
    val following: Int
)