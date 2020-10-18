package com.example.androidarchitecturedemo.viewModel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel: ViewModel() {

    private var _isFavorite = MutableLiveData<Boolean>()

    val isFavorite: LiveData<Boolean> = _isFavorite

    private var __isFavorite: Boolean = false

    fun onFavoriteSelected() {
        __isFavorite = !__isFavorite
        _isFavorite.value = __isFavorite
    }

}