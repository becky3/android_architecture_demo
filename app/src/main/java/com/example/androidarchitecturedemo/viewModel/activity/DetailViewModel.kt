package com.example.androidarchitecturedemo.viewModel.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.androidarchitecturedemo.room.database.FavoriteDatabase
import com.example.androidarchitecturedemo.room.entity.FavoriteRoomItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    application: Application
): AndroidViewModel(application)
{
    private var _isFavorite = MutableLiveData<Boolean>()
    private var repositoryId: Int = -1
    private var favoriteRoomItem: FavoriteRoomItem? = null
    private val favoriteDatabase: FavoriteDatabase = Room.databaseBuilder(
        application.applicationContext,
        FavoriteDatabase::class.java, "database-name"
    ).build()
    private val favoriteRepositoryDao = favoriteDatabase.favoriteRepositoryDao()

    val isFavorite: LiveData<Boolean> = _isFavorite

    fun setup(repositoryId: Int) {
        this.repositoryId = repositoryId
    }

    fun onFavoriteSelected() {
        val isFavorite = _isFavorite.value ?: return

        CoroutineScope(Dispatchers.IO).launch {
            if (isFavorite && favoriteRoomItem != null) {
                favoriteRepositoryDao.delete(favoriteRoomItem!!)
            } else {
                val newItem = FavoriteRoomItem(repositoryId)
                favoriteRepositoryDao.insertAll(newItem)
            }
            updateFavorite()
        }
    }

    fun onCreateOptionsMenu() {
        updateFavorite()
    }

    private fun updateFavorite() {
        CoroutineScope(Dispatchers.IO).launch {
            val favorites = favoriteRepositoryDao.getAll()
            favoriteRoomItem = favorites.firstOrNull { it.repositoryId == repositoryId }
            val isFavorite = favoriteRoomItem != null
            _isFavorite.postValue(isFavorite)
        }
    }

}