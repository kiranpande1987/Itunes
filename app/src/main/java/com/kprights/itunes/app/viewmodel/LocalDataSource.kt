package com.kprights.itunes.app.viewmodel

import androidx.lifecycle.LiveData
import com.kprights.itunes.app.common.EntryDao
import com.kprights.itunes.app.model.DBEntry


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 16/04/20
 * Time : 12:42 PM
 */

class LocalDataSource(private val database: EntryDao) : IDataSource {

    fun saveEntries(dbEntry: DBEntry) {
        database.insert(entry = dbEntry)
    }

    fun getAllEntries(): LiveData<List<DBEntry>> {
        return database.getAllEntries()
    }

    fun deleteAllEntries() {
        database.deleteAllEntries()
    }
}