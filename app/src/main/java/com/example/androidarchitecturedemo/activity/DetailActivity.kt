package com.example.androidarchitecturedemo.activity

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androidarchitecturedemo.R
import com.example.androidarchitecturedemo.entity.GitRepositoryInfo
import com.example.androidarchitecturedemo.viewModel.activity.DetailViewModel

class DetailActivity : AppCompatActivity() {

    enum class ExtraKey(val key: String) {
        RepositoryInfo("RepositoryInfo")
    }

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var webView: WebView
    private lateinit var repositoryInfo: GitRepositoryInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getExtras()
        linkViews()
        setupViews()
        bindViewModel()
    }

    private fun getExtras() {
        repositoryInfo = intent.getSerializableExtra(ExtraKey.RepositoryInfo.key) as GitRepositoryInfo
    }

    private fun linkViews() {
        webView = findViewById(R.id.web_view)
    }

    private fun setupViews() {
        title = repositoryInfo.name
        webView.loadUrl(repositoryInfo.url.toString())
    }

    private fun bindViewModel() {

    }
}