package com.kafka.githubbasic.data.user

import com.kafka.githubbasic.data.user.model.RepositoryResponseModel
import com.kafka.githubbasic.data.user.model.SearchUsersResponseModel
import com.kafka.githubbasic.data.user.model.UserDetailResponseModel
import com.kafka.githubbasic.data.user.model.UserListResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("users")
    suspend fun getUsers(): List<UserListResponseModel>

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") searchQuery: String
    ): SearchUsersResponseModel

    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String
    ): UserDetailResponseModel

    @GET("users/{username}/repos")
    suspend fun getRepos(
        @Path("username") username: String
    ): List<RepositoryResponseModel>
}