package com.example.androidarchitecturedemo.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidarchitecturedemo.room.dao.FavoriteRepositoryDao
import com.example.androidarchitecturedemo.room.entity.FavoriteRoomItem

@Database(entities = [FavoriteRoomItem::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteRepositoryDao(): FavoriteRepositoryDao
}