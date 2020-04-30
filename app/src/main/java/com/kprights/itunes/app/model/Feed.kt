package com.kprights.itunes.app.model

import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("author") val author: Author,
    @SerializedName("entry") val entry: List<Entry>,
    @SerializedName("updated") val updated: Updated,
    @SerializedName("rights") val rights: Rights,
    @SerializedName("title") val title: Title,
    @SerializedName("icon") val icon: Icon,
    @SerializedName("link") val link: List<Link>,
    @SerializedName("id") val id: Id
)