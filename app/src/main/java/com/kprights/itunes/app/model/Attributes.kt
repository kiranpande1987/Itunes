package com.kprights.itunes.app.model

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("rel") val rel: String,
    @SerializedName("type") val type: String,
    @SerializedName("href") val href: String
)