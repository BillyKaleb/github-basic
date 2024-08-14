package com.kafka.githubbasic.presentation.userdetail

import androidx.lifecycle.ViewModel
import com.kafka.githubbasic.domain.user.usecase.GetUserDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetails: GetUserDetails
) : ViewModel() {

    fun getUserDetails(username: String) {
        // TODO do logic
    }
}