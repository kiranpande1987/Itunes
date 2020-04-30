package com.kprights.itunes.app.model

import com.google.gson.annotations.SerializedName

data class Entry(
    @SerializedName("im:name") val name: Name,
    @SerializedName("rights") val rights: Rights,
    @SerializedName("im:price") val price: Price,
    @SerializedName("im:image") val image: List<Image>,
    @SerializedName("im:artist") val artist: Artist,
    @SerializedName("title") val title: Title,
    @SerializedName("link") val link: Link,
    @SerializedName("id") val id: Id,
    @SerializedName("im:contentType") val contentType: ContentType,
    @SerializedName("category") val category: Category,
    @SerializedName("im:releaseDate") val releaseDate: ReleaseDate
)