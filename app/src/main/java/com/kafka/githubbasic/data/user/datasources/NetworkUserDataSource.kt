package com.kafka.githubbasic.data.user.datasources

import com.kafka.githubbasic.data.user.UserService
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

    suspend fun searchUsers(keyword: String): Flow<List<UserListResponseModel>> {
        return flow { emit(userService.searchUsers(keyword).items) }
    }

    suspend fun getUserDetails(username: String): Flow<UserDetailResponseModel> {
        return flow { emit(userService.getUserDetails(username)) }
    }
}