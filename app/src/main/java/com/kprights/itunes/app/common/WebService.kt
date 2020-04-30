package com.kprights.itunes.app.common

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kprights.itunes.app.model.BaseModel
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 10/04/20
 * Time : 11:40 PM
 */

private const val BASE_URL = "https://itunes.apple.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

val WebService: Api by lazy { retrofit.create(Api::class.java) }

interface Api {
    //
    // Put various Web API Calls of GET and POST
    //
    @GET("us/rss/newfreeapplications/limit=2/json")
    fun getFeed(): Deferred<BaseModel>
}