package com.kafka.githubbasic.presentation.userdetail

import com.kafka.githubbasic.presentation.userdetail.model.UseDetailFragmentModel

sealed class UserDetailsViewState {

    data object Idle : UserDetailsViewState()

    data object Loading : UserDetailsViewState()

    data class Success(val data: UseDetailFragmentModel) : UserDetailsViewState()

    data class SuccessRepopulate(val data: UseDetailFragmentModel) : UserDetailsViewState()

    data class ErrorNetwork(val throwable: Throwable) : UserDetailsViewState()
}