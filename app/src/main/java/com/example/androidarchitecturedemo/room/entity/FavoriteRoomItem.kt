package com.example.androidarchitecturedemo.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteRoomItem (

    @PrimaryKey
    @ColumnInfo(name = "repository_id")
    val repositoryId: Int

)