package com.kafka.githubbasic.presentation.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kafka.githubbasic.databinding.FragmentUserBinding
import com.kafka.githubbasic.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>() {

    private val viewModel: UserViewModel by viewModels()

    private val userAdapter by lazy { UserAdapter() }

    override fun setupViews() {
        binding.apply {
            rvGithubUsers.let {
                it.layoutManager = LinearLayoutManager(context)
                it.adapter = userAdapter
            }
        }
    }

    override fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is UserViewState.Success -> {
                    userAdapter.addList(it.data.listResponse)
                }

                is UserViewState.ErrorNetwork -> {
                    // TODO Handle error
                }

                is UserViewState.Idle -> {
                    // TODO no op yet
                }

                is UserViewState.Loading -> {
                    // TODO add loading state
                }
            }
        }
    }

    override fun init() {
        viewModel.getUsers()
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean
    ): FragmentUserBinding = FragmentUserBinding.inflate(layoutInflater, container, attachToParent)
}