package com.kafka.githubbasic.data.user

import com.kafka.githubbasic.data.user.model.SearchUsersResponseModel
import com.kafka.githubbasic.data.user.model.UserListResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("users")
    suspend fun getUsers(): List<UserListResponseModel>

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") searchQuery: String
    ): SearchUsersResponseModel
}