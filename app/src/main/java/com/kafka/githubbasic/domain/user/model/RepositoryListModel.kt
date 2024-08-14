package com.kafka.githubbasic.domain.user.model

data class RepositoryListModel(
    val name: String,
    val language: String?,
    val starCount: Int,
    val description: String?,
    val url: String
)