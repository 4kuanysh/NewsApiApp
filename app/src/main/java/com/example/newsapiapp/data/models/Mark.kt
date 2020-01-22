package com.example.newsapiapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "Mark")
data class Mark(
    @PrimaryKey
    val title: String
)