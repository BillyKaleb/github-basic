package com.kafka.githubbasic.domain.user.usecase

import com.kafka.githubbasic.domain.base.BaseUseCase
import com.kafka.githubbasic.domain.user.UserRepository
import com.kafka.githubbasic.domain.user.model.UserListModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsers @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<List<UserListModel>, Unit>() {

    override suspend fun run(params: Unit): Flow<List<UserListModel>> {
        return userRepository.getUser()
    }
}