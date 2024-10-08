package com.kafka.githubbasic.data.user.datasources

import com.kafka.githubbasic.data.user.UserService
import com.kafka.githubbasic.data.user.model.RepositoryResponseModel
import com.kafka.githubbasic.data.user.model.SearchUsersResponseModel
import com.kafka.githubbasic.data.user.model.UserDetailResponseModel
import com.kafka.githubbasic.data.user.model.UserListResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkUserDataSource @Inject constructor(
    private val userService: UserService
) {

    suspend fun getUsers(): Flow<List<UserListResponseModel>> {
        return flow { emit(userService.getUsers()) }
    }

    suspend fun searchUsers(keyword: String): Flow<SearchUsersResponseModel> {
        return flow { emit(userService.searchUsers(keyword)) }
    }

    suspend fun getUserDetails(username: String): Flow<UserDetailResponseModel> {
        return flow { emit(userService.getUserDetails(username)) }
    }

    suspend fun getRepos(username: String): Flow<List<RepositoryResponseModel>> {
        return flow { emit(userService.getRepos(username)) }
    }
}