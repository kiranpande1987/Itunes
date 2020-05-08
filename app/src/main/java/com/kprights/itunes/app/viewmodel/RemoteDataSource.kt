package com.kprights.itunes.app.viewmodel

import com.kprights.itunes.app.common.WebService
import com.kprights.itunes.app.model.BaseModel


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 16/04/20
 * Time : 12:42 PM
 */

class RemoteDataSource : IDataSource {

    suspend fun getFeed(): BaseModel {
        val deferred = WebService.getFeed()
        return deferred.await()
    }
}