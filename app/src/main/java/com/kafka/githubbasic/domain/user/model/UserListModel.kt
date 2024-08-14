package com.kafka.githubbasic.domain.user.model

data class UserListModel(
    val name: String,
    val id: Int,
    val avatarUrl: String,
    val fullname: String  = "",
    val follower: Int = 0,
    val following: Int = 0
)