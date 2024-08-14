package com.kafka.githubbasic.data.user

import com.kafka.githubbasic.data.user.model.UserListResponseModel
import retrofit2.http.GET

interface UserService {

    @GET("users")
    suspend fun getUsers(): UserListResponseModel
}