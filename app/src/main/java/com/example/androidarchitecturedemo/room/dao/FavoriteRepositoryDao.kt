package com.example.androidarchitecturedemo.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.androidarchitecturedemo.room.entity.FavoriteRoomItem

@Dao
interface FavoriteRepositoryDao {

    @Query("SELECT * FROM favoriteroomitem")
    suspend fun getAll(): List<FavoriteRoomItem>

    @Insert
    suspend fun insertAll(vararg favoriteRoomItem: FavoriteRoomItem)

    @Delete
    suspend fun delete(favoriteRoomItem: FavoriteRoomItem)

}