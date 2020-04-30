package com.kprights.itunes.app.model

import com.google.gson.annotations.SerializedName

data class Rights(
    @SerializedName("label") val label: String
)