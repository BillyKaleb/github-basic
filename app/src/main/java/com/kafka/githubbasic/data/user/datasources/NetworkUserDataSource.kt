package com.kafka.githubbasic.data.user.datasources

import com.kafka.githubbasic.data.user.UserService
import com.kafka.githubbasic.data.user.model.UserListResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkUserDataSource @Inject constructor(
    private val userService: UserService
) {

    suspend fun getUsers(): Flow<UserListResponseModel> {
        return flow { userService.getUsers() }
    }
}