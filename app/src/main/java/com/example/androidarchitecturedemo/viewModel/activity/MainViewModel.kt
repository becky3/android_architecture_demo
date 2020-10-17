package com.example.androidarchitecturedemo.viewModel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.androidarchitecturedemo.common.PublishLiveData
import com.example.androidarchitecturedemo.entity.GitRepositoryInfo

class MainViewModel: ViewModel() {

    private val _repositoryInfo = PublishLiveData<GitRepositoryInfo>()

    val repositoryInfo :LiveData<GitRepositoryInfo> = _repositoryInfo

    fun onSelectedItem(item: GitRepositoryInfo) {
        _repositoryInfo.value = item
    }

}