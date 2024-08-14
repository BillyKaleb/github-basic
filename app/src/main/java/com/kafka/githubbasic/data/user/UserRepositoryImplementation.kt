package com.kafka.githubbasic.data.user

import com.kafka.githubbasic.data.user.datasources.NetworkUserDataSource
import com.kafka.githubbasic.domain.user.UserRepository
import com.kafka.githubbasic.domain.user.model.UserListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImplementation @Inject constructor(
    private val networkUserDataSource: NetworkUserDataSource
) : UserRepository {

    override suspend fun getUser(): Flow<UserListModel> {
        return networkUserDataSource.getUsers().map {
            UserListModel(
                name = it.name,
                id = it.id,
                avatarUrl = it.avatar_id
            )
        }
    }
}