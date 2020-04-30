package com.kprights.itunes.app.model

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("label") val label: String,
    @SerializedName("attributes") val attributes: Attributes
)