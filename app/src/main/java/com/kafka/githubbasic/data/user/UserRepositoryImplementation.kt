package com.kafka.githubbasic.data.user

import com.kafka.githubbasic.data.user.datasources.NetworkUserDataSource
import com.kafka.githubbasic.domain.user.UserRepository
import com.kafka.githubbasic.domain.user.model.RepositoryListModel
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
                    name = userListResponse.login ?: "",
                    id = userListResponse.id,
                    avatarUrl = userListResponse.avatarUrl ?: ""
                )
            }
        }
    }

    override suspend fun searchUser(keyword: String): Flow<List<UserListModel>> {
        return networkUserDataSource.searchUsers(keyword).map {
            it.items?.map { userListResponse ->
                UserListModel(
                    name = userListResponse.login ?: "",
                    id = userListResponse.id,
                    avatarUrl = userListResponse.avatarUrl ?: ""
                )
            } ?: emptyList()
        }
    }

    override suspend fun getUserDetails(username: String): Flow<UserListModel> {
        return networkUserDataSource.getUserDetails(username).map { detail ->
            UserListModel(
                name = detail.login ?: "",
                id = detail.id,
                avatarUrl = detail.avatarUrl ?: "",
                fullname = detail.name ?: "",
                follower = detail.followerCount ?: 0,
                following = detail.followingCount ?: 0
            )
        }
    }

    override suspend fun getRepos(username: String): Flow<List<RepositoryListModel>> {
        return networkUserDataSource.getRepos(username).map {
            it.map { repoDetail ->
                RepositoryListModel(
                    name = repoDetail.name ?: "",
                    language = repoDetail.language,
                    starCount = repoDetail.stargazersCount ?: 0,
                    description = repoDetail.description,
                    url = repoDetail.htmlUrl ?: "",
                    fork = repoDetail.fork
                )
            }
        }
    }
}