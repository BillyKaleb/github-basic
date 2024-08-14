package com.kafka.githubbasic.presentation.userdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kafka.githubbasic.databinding.FragmentUserDetailBinding
import com.kafka.githubbasic.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentUserDetailBinding>() {

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
    ): FragmentUserDetailBinding =
        FragmentUserDetailBinding.inflate(layoutInflater, container, attachToParent)

    companion object {
        const val USERNAME = "USERNAME"
    }
}