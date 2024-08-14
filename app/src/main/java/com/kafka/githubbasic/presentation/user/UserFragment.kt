package com.kafka.githubbasic.presentation.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kafka.githubbasic.databinding.FragmentUserBinding
import com.kafka.githubbasic.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>() {

    private val viewModel: UserViewModel by viewModels()

    private val userAdapter by lazy { UserAdapter() }

    override fun setupViews() {
        TODO("Not yet implemented")
    }

    override fun setupObservers() {
        TODO("Not yet implemented")
    }

    override fun init() {
        TODO("Not yet implemented")
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean
    ): FragmentUserBinding = FragmentUserBinding.inflate(layoutInflater, container, attachToParent)
}