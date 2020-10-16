package com.example.androidarchitecturedemo.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidarchitecturedemo.entity.GitRepositoryInfo
import com.example.androidarchitecturedemo.model.GithubModel

class SearchViewModel: ViewModel() {

    private val githubModel = GithubModel()

    private val _gitRepositoryList = MutableLiveData<List<GitRepositoryInfo>>()
    private val _errorMessage = MutableLiveData<String>()

    val gitRepositoryList = _gitRepositoryList as LiveData<List<GitRepositoryInfo>>
    val errorMessage = _errorMessage as LiveData<String>

    fun onQueryTextSubmit(query: String) {
        githubModel.searchRepositories(
            query,
            { list ->

                _gitRepositoryList.postValue(list)
            },
            {
                _errorMessage.postValue("通信に失敗しました")
            }
        )
    }

}