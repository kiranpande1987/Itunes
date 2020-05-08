package com.kprights.itunes.app.viewmodel

import androidx.lifecycle.LiveData
import com.kprights.itunes.app.common.EntryDao
import com.kprights.itunes.app.model.BaseModel


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 16/04/20
 * Time : 12:42 PM
 */

class LocalDataSource(private val database: EntryDao) : IDataSource {

    suspend fun saveEntries(baseModel: BaseModel) {
        database.insert(baseModel = baseModel)
    }

    fun getAllEntries(): LiveData<BaseModel> {
        return database.getAllEntries()
    }

    suspend fun deleteAllEntries() {
        database.deleteAllEntries()
    }
}