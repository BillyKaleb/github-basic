package com.kafka.githubbasic.domain.user.usecase

import com.kafka.githubbasic.domain.base.BaseUseCase
import com.kafka.githubbasic.domain.user.UserRepository
import com.kafka.githubbasic.domain.user.model.UserListModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUser @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<List<UserListModel>, SearchUser.Params>() {

    override suspend fun run(params: Params): Flow<List<UserListModel>> {
        return userRepository.searchUser(params.keyword)
    }

    data class Params(
        val keyword: String
    )
}