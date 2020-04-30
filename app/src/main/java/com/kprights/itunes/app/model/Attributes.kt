package com.kprights.itunes.app.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Attributes(
    @SerializedName("rel") val rel: String,
    @SerializedName("type") val type: String,
    @SerializedName("href") val href: String,
    @SerializedName("amount") val amount: String,
    @SerializedName("currency") val currency: String
) : Parcelable