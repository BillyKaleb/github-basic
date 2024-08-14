package com.kafka.githubbasic.presentation.userdetail.model

data class UserRepoFragmentModel(
    val name: String,
    val language: String,
    val starCount: Int,
    val description: String,
    val url: String
)