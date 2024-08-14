package com.kafka.githubbasic.presentation.userdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.kafka.githubbasic.databinding.FragmentUserDetailBinding
import com.kafka.githubbasic.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentUserDetailBinding>() {

    private val viewModel: UserDetailViewModel by viewModels()

    override fun setupViews() {
        // no op
    }

    override fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is UserDetailsViewState.ErrorNetwork -> {

                }

                is UserDetailsViewState.Idle -> {

                }

                is UserDetailsViewState.Loading -> {

                }

                is UserDetailsViewState.Success -> {
                    binding.run {
                        it.data.let { userDetail ->
                            tvDetailUserName.text = "Username: ${userDetail.name}"
                            tvDetailFullName.text = "Fullname: ${userDetail.fullname}"
                            tvDetailFollower.text = "Follower: ${userDetail.follower}"
                            tvDetailFollowing.text = "Following: ${userDetail.following}"
                            Glide.with(requireContext())
                                .load(userDetail.avatarUrl)
                                .into(ivUserDetail)
                        }
                    }
                }

                is UserDetailsViewState.SuccessRepopulate -> {

                }
            }
        }
    }

    override fun init() {
        arguments?.getString(USERNAME, "")?.let {
            viewModel.getUserDetails(it)
        }
    }

    override fun getViewBinding(
        layoutInflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean
    ): FragmentUserDetailBinding =
        FragmentUserDetailBinding.inflate(layoutInflater, container, attachToParent)

    companion object {
        const val USERNAME = "USERNAME"
    }
}