package com.kafka.githubbasic.presentation.userdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.kafka.githubbasic.databinding.FragmentUserDetailBinding
import com.kafka.githubbasic.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentUserDetailBinding>() {

    private val viewModel: UserDetailViewModel by viewModels()

    private val userRepoAdapter by lazy {
        UserRepoAdapter {
            openWebView(it)
        }
    }

    override fun setupViews() {
        binding.apply {
            rvRepository.let {
                it.layoutManager = LinearLayoutManager(context)
                it.adapter = userRepoAdapter
            }
        }
    }

    private fun openWebView(url: String) {
        Toast.makeText(requireContext(), "Load WebView " + url, Toast.LENGTH_SHORT).show()
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

                is UserDetailsViewState.SuccessLoadRepo -> {
                    userRepoAdapter.submitList(it.data)
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