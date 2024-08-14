package com.kafka.githubbasic.presentation.user

import com.kafka.githubbasic.presentation.user.model.UserFragmentModel

sealed class UserViewState {

    data object Idle : UserViewState()

    data object Loading : UserViewState()

    data class Success(val data: UserFragmentModel) : UserViewState()

    data class SuccessRepopulate(val data: UserFragmentModel): UserViewState()

    data class ErrorNetwork(val throwable: Throwable) : UserViewState()
}