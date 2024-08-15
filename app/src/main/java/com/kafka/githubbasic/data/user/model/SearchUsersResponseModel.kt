package com.kafka.githubbasic.data.user.model

import com.google.gson.annotations.SerializedName

data class SearchUsersResponseModel(
    @SerializedName("items") val items: List<UserListResponseModel>?
)