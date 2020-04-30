package com.kprights.itunes.app.model

import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("attributes") val attributes: Attributes
)