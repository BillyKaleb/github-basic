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

    override suspend fun getUser(): Flow<List<UserListModel>> {
        return networkUserDataSource.getUsers().map {
            it.map { userListResponse ->
                UserListModel(
                    name = userListResponse.login,
                    id = userListResponse.id,
                    avatarUrl = userListResponse.avatar_url
                )
            }
        }
    }

    override suspend fun searchUser(keyword: String): Flow<List<UserListModel>> {
        return networkUserDataSource.searchUsers(keyword).map {
            it.map { userListResponse ->
                UserListModel(
                    name = userListResponse.login,
                    id = userListResponse.id,
                    avatarUrl = userListResponse.avatar_url
                )
            }
        }
    }

    override suspend fun getUserDetails(username: String): Flow<UserListModel> {
        return networkUserDataSource.getUserDetails(username).map { detail ->
            UserListModel(
                name = detail.login,
                id = detail.id,
                avatarUrl = detail.avatar_url,
                fullname = detail.name,
                follower = detail.followers,
                following = detail.following
            )
        }
    }
}