package com.kprights.itunes.app.model

import com.google.gson.annotations.SerializedName

data class BaseModel(
    @SerializedName("feed") val feed: Feed
)