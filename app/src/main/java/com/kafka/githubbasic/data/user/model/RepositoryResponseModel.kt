package com.kafka.githubbasic.data.user.model

data class RepositoryResponseModel(
    val name: String,
    val language: String?,
    val stargazers_count: Int,
    val description: String?,
    val html_url: String
)