package com.kafka.githubbasic.presentation

import com.kafka.githubbasic.databinding.ActivityMainBinding
import com.kafka.githubbasic.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}