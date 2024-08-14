package com.kafka.githubbasic.presentation.userdetail.model

data class UseDetailFragmentModel(
    val name: String,
    val id: Int,
    val avatarUrl: String,
    val fullname: String = "",
    val follower: Int = 0,
    val following: Int = 0
)