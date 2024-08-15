package com.kafka.githubbasic.data.user.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponseModel(
    @SerializedName("name") val name: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("stargazers_count") val stargazersCount: Int?,
    @SerializedName("description") val description: String?,
    @SerializedName("html_url") val htmlUrl: String?,
    @SerializedName("fork") val fork: Boolean
)