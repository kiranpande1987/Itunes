package com.kprights.itunes.app.model

import androidx.room.TypeConverter
import com.google.gson.Gson


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 08/05/20
 * Time : 11:02 PM
 */

class Convertors {
    @TypeConverter
    fun FeedToJson(feed: Feed) = Gson().toJson(feed)

    @TypeConverter
    fun jsonToFeed(value: String) = Gson().fromJson(value, Feed::class.java)
}