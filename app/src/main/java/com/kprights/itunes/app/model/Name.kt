package com.kprights.itunes.app.model

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("label") val label: String
)