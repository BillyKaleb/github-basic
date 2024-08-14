package com.kafka.githubbasic.presentation.userdetail

import com.kafka.githubbasic.presentation.userdetail.model.UseDetailFragmentModel
import com.kafka.githubbasic.presentation.userdetail.model.UserRepoFragmentModel

sealed class UserDetailsViewState {

    data object Idle : UserDetailsViewState()

    data object Loading : UserDetailsViewState()

    data class Success(val data: UseDetailFragmentModel) : UserDetailsViewState()

    data class SuccessLoadRepo(val data: List<UserRepoFragmentModel>): UserDetailsViewState()

    data class ErrorNetwork(val throwable: Throwable) : UserDetailsViewState()
}