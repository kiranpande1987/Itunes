package com.kprights.itunes.app.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 01/05/20
 * Time : 9:27 PM
 */

@Parcelize
@Entity
data class DBEntry(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var name: String = "",
    var image: String = "",
    var currency: String = "",
    var amount: String = "",
    var artist: String = ""
) : Parcelable