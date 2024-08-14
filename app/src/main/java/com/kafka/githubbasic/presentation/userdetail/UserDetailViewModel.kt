package com.kafka.githubbasic.presentation.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kafka.githubbasic.domain.user.usecase.GetUserDetails
import com.kafka.githubbasic.presentation.userdetail.model.UseDetailFragmentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetails: GetUserDetails
) : ViewModel() {

    private val _viewState = MutableLiveData<UserDetailsViewState>()

    val viewState: LiveData<UserDetailsViewState> = _viewState

    fun getUserDetails(username: String) {
        _viewState.value = UserDetailsViewState.Loading
        getUserDetails.invoke(GetUserDetails.Params(username), viewModelScope, onResult = {
            _viewState.value = UserDetailsViewState.Success(
                UseDetailFragmentModel(
                    it.name, it.id, it.avatarUrl, it.fullname, it.follower, it.following
                )

            )
        }, onError = {
            _viewState.value = UserDetailsViewState.ErrorNetwork(it)
        })
    }
}