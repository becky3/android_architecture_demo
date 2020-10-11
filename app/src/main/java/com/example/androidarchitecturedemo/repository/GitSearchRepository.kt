package com.example.androidarchitecturedemo.repository

import com.example.androidarchitecturedemo.api.GithubApiSessionClient

class GitSearchRepository {

    private val githubApiSessionClient = GithubApiSessionClient()

    fun searchRepositories(searchWord: String) {

        githubApiSessionClient.searchRepositories(searchWord)

    }
}