package com.example.androidarchitecturedemo.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidarchitecturedemo.common.PublishLiveData
import com.example.androidarchitecturedemo.entity.GitRepositoryInfo
import com.example.androidarchitecturedemo.model.GithubModel
import com.example.androidarchitecturedemo.repository.ApiResult

class SearchViewModel : ViewModel() {

    private val githubModel = GithubModel()

    private val _gitRepositoryList = MutableLiveData<List<GitRepositoryInfo>>()
    private val _errorMessage = PublishLiveData<String>()

    val gitRepositoryList = _gitRepositoryList as LiveData<List<GitRepositoryInfo>>
    val errorMessage = _errorMessage as LiveData<String>

    fun onQueryTextSubmit(query: String) {

        githubModel.searchRepositories(query, object:ApiResult<List<GitRepositoryInfo>>() {
            override fun success(result: List<GitRepositoryInfo>) {
                _gitRepositoryList.postValue(result)
            }
            override fun failed(exception: Exception) {
                _errorMessage.postValue(exception.message)
            }
        })
    }

}