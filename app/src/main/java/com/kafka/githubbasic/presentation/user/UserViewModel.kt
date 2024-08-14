package com.kafka.githubbasic.presentation.user

import androidx.lifecycle.ViewModel
import com.kafka.githubbasic.domain.user.usecase.GetUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsers: GetUsers
): ViewModel() {
}