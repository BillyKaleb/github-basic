package com.kafka.githubbasic.domain.user

import com.kafka.githubbasic.domain.user.model.UserListModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUser(): Flow<List<UserListModel>>

    suspend fun searchUser(keyword: String): Flow<List<UserListModel>>

    suspend fun getUserDetails(username: String): Flow<UserListModel>
}