package com.kafka.githubbasic.data.user.model

import com.google.gson.annotations.SerializedName

data class UserListResponseModel(
    @SerializedName("login") val login: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String?
)