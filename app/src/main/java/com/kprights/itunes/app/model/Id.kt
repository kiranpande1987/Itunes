package com.kprights.itunes.app.model

import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("label") val label: String
)