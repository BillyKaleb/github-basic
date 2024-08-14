package com.kafka.githubbasic.presentation

import android.os.Bundle
import com.kafka.githubbasic.databinding.ActivityWebviewBinding
import com.kafka.githubbasic.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity : BaseActivity<ActivityWebviewBinding>() {

    override fun getViewBinding(): ActivityWebviewBinding =
        ActivityWebviewBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.getStringExtra(URL)?.let {
            binding.webview.loadUrl(it)
        }
    }

    companion object {
        const val URL = "URL"
    }
}