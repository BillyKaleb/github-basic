package com.kafka.githubbasic.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null

    val binding: VB
        get() {
            return _binding ?: throw Exception("ViewBinding Null")
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = getViewBinding(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        setupObservers()
        init()
    }

    abstract fun setupViews()

    abstract fun setupObservers()

    abstract fun init()

    abstract fun getViewBinding(
        layoutInflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean
    ): VB

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}