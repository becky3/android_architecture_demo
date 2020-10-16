package com.example.androidarchitecturedemo.model

import com.example.androidarchitecturedemo.entity.GitRepositoryInfo
import com.example.androidarchitecturedemo.repository.GitSearchRepository

class GithubModel {

    private val gitSearchRepository = GitSearchRepository()

    fun searchRepositories(
        searchWord: String,
        onSuccess: (List<GitRepositoryInfo>) -> Unit,
        onFailure: () -> Unit
    ) {
        gitSearchRepository.searchRepositories(
            searchWord,
            onSuccess,
            onFailure
        )
    }

}