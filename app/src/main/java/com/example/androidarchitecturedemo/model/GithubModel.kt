package com.example.androidarchitecturedemo.model

import com.example.androidarchitecturedemo.entity.GitRepositoryInfo
import com.example.androidarchitecturedemo.repository.ApiResult
import com.example.androidarchitecturedemo.repository.GitSearchRepository

class GithubModel {

    private val gitSearchRepository = GitSearchRepository()

    fun searchRepositories(
        searchWord: String,
        result: ApiResult<List<GitRepositoryInfo>>
    ) {
        gitSearchRepository.searchRepositories(
            searchWord,
            result
        )
    }

}