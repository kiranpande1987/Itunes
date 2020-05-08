package com.kprights.itunes.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class BaseModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @SerializedName("feed") val feed: Feed
)