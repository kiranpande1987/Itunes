package com.kprights.itunes.app.model

import com.google.gson.annotations.SerializedName

data class Updated(
    @SerializedName("label") val label: String
)