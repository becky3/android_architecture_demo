package com.example.androidarchitecturedemo.repository

import com.example.androidarchitecturedemo.api.GithubApiSessionClient
import com.example.androidarchitecturedemo.entity.GitRepositoryInfo

class GitSearchRepository {

    private val githubApiSessionClient = GithubApiSessionClient()

    fun searchRepositories(
        searchWord: String,
        onSuccess: (List<GitRepositoryInfo>) -> Unit,
        onFailure: () -> Unit
    ) {

        githubApiSessionClient.searchRepositories(
            searchWord,
            onSuccess,
            onFailure
        )

    }
}