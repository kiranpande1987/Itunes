package com.kprights.itunes.app.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    @SerializedName("attributes") val attributes: Attributes
) : Parcelable