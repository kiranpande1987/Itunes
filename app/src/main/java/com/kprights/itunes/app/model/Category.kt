package com.kprights.itunes.app.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("attributes") val attributes: Attributes
)