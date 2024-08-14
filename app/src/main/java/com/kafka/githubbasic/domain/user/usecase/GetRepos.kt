package com.kafka.githubbasic.domain.user.usecase

import com.kafka.githubbasic.domain.base.BaseUseCase
import com.kafka.githubbasic.domain.user.UserRepository
import com.kafka.githubbasic.domain.user.model.RepositoryListModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepos @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<List<RepositoryListModel>, GetRepos.Params>() {

    override suspend fun run(params: Params): Flow<List<RepositoryListModel>> {
        return userRepository.getRepos(params.username)
    }

    data class Params(
        val username: String
    )
}