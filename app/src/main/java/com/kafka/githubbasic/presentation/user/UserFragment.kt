package com.kafka.githubbasic.presentation.user

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kafka.githubbasic.R
import com.kafka.githubbasic.databinding.FragmentUserBinding
import com.kafka.githubbasic.presentation.base.BaseFragment
import com.kafka.githubbasic.presentation.userdetail.UserDetailFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>() {

    private val viewModel: UserViewModel by viewModels()

    private val userAdapter by lazy {
        UserAdapter {
            openUserDetailFragment(it)
        }
    }

    private fun openUserDetailFragment(username: String) {
        val bundle = bundleOf(
            UserDetailFragment.USERNAME to username
        )
        findNavController().navigate(
            R.id.action_fragment_user_to_userDetailFragment, bundle
        )
    }

    override fun setupViews() {
        binding.apply {
            rvGithubUsers.let {
                it.layoutManager = LinearLayoutManager(context)
                it.adapter = userAdapter
            }

            btnSearchUser.setOnClickListener {
                etSearchUser.text?.toString()?.run {
                    doSearchFromSearchBar(etSearchUser, this)
                }
            }

            etSearchUser.setOnKeyListener { _, keyCode, keyEvent ->
                if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    etSearchUser.text?.toString()?.run {
                        doSearchFromSearchBar(etSearchUser, this)
                    }
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
    }

    private fun doSearchFromSearchBar(view: View, keyword: String) {
        view.clearFocus()
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
        viewModel.searchUsers(keyword)
    }

    override fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is UserViewState.Success -> {
                    userAdapter.submitList(it.data.listResponse)
                }

                is UserViewState.ErrorNetwork -> {
                    Toast.makeText(requireContext(), it.throwable.message, Toast.LENGTH_LONG).show()
                }

                is UserViewState.Idle -> {
                    // TODO no op yet
                }

                is UserViewState.Loading -> {
                    // TODO add loading state
                }

                is UserViewState.SuccessRepopulate -> {
                    userAdapter.submitList(it.data.listResponse)
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