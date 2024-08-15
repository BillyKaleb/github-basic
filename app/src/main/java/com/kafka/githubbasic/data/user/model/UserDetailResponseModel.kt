package com.kafka.githubbasic.data.user.model

import com.google.gson.annotations.SerializedName

data class UserDetailResponseModel(
    @SerializedName("login") val login: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("followers")  val followerCount: Int?,
    @SerializedName("following") val followingCount: Int?
)