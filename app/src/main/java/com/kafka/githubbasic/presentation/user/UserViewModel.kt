package com.kafka.githubbasic.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kafka.githubbasic.domain.user.usecase.GetUsers
import com.kafka.githubbasic.presentation.user.model.UserFragmentModel
import com.kafka.githubbasic.presentation.user.model.toUserAdapterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsers: GetUsers
) : ViewModel() {

    private val _viewState = MutableLiveData<UserViewState>()

    val viewState: LiveData<UserViewState> = _viewState

    fun getUsers() {
        _viewState.value = UserViewState.Loading
        getUsers.invoke(Unit, viewModelScope, onResult = {
            _viewState.value = UserViewState.Success(
                UserFragmentModel(it.map { userList ->
                    userList.toUserAdapterModel()
                })

            )
        }, onError = {
            _viewState.value = UserViewState.ErrorNetwork(it)
        })
    }
}