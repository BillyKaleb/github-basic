package com.kafka.githubbasic.presentation.userdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.kafka.githubbasic.R
import com.kafka.githubbasic.databinding.FragmentUserDetailBinding
import com.kafka.githubbasic.presentation.WebViewActivity
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
        val bundle = bundleOf(
            WebViewActivity.URL to url
        )
        findNavController().navigate(
            R.id.action_userDetailFragment_to_webView, bundle
        )
    }

    override fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is UserDetailsViewState.ErrorNetwork -> {
                    // TODO handle error
                }

                is UserDetailsViewState.Idle -> {
                    // TODO handle idle
                }

                is UserDetailsViewState.Loading -> {
                    // TODO Handle Loading
                }

                is UserDetailsViewState.Success -> {
                    binding.run {
                        it.data.let { userDetail ->
                            tvDetailUserName.text = getString(
                                R.string.user_detail_username_textview,
                                userDetail.name
                            )

                            tvDetailFullName.text = getString(
                                R.string.user_detail_fullname_textview,
                                userDetail.fullname
                            )

                            tvDetailFollower.text = getString(
                                R.string.user_detail_follower_textview,
                                userDetail.follower.toString()
                            )

                            tvDetailFollowing.text = getString(
                                R.string.user_detail_following_textview,
                                userDetail.following.toString()
                            )

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