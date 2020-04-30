package com.kprights.itunes.app.model

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("name") val name: Name,
    @SerializedName("uri") val uri: Uri
)