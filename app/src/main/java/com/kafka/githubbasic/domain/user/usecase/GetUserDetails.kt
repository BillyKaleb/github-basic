package com.kafka.githubbasic.domain.user.usecase

import com.kafka.githubbasic.domain.base.BaseUseCase
import com.kafka.githubbasic.domain.user.UserRepository
import com.kafka.githubbasic.domain.user.model.UserListModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetails @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<UserListModel, GetUserDetails.Params>() {

    override suspend fun run(params: Params): Flow<UserListModel> {
        return userRepository.getUserDetails(params.username)
    }

    data class Params(
        val username: String
    )
}