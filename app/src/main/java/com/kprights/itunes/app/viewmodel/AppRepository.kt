package com.kprights.itunes.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kprights.itunes.app.common.EntryDao
import com.kprights.itunes.app.model.BaseModel
import com.kprights.itunes.app.model.DBEntry
import kotlinx.coroutines.*
import timber.log.Timber


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/04/20
 * Time : 11:00 PM
 */

class AppRepository(database: EntryDao) {

    enum class ApiStatus { LOADING, ERROR, DONE }

    private val remoteDataSource = RemoteDataSource()
    private val localDataSource = LocalDataSource(database)

    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    val status: MutableLiveData<ApiStatus> = MutableLiveData<ApiStatus>()
    val dbEntries: LiveData<List<DBEntry>> =
        Transformations.map(localDataSource.getAllEntries()) { it }

    init {
        scope.launch {

            try {
                Timber.e("1")
                status.value = ApiStatus.LOADING
                Timber.e("2")
                val model = remoteDataSource.getFeed()
                Timber.e("3")
                localDataSource.deleteAllEntries()
                Timber.e("4")

                withContext(Dispatchers.IO)
                {
                    insertIntoDB(model)
                }

                Timber.e("5")
                status.value = ApiStatus.DONE
                Timber.e("Call Complete")
            } catch (e: Exception) {
                status.value = ApiStatus.ERROR
                Timber.e("Exc : ${e.message}")
            }
        }
    }

    private suspend fun insertIntoDB(model: BaseModel) {
        Timber.e("Size : ${model.feed.entry.size}")
        model.feed.entry.forEach {
            localDataSource.saveEntries(
                DBEntry().copy(
                    name = it.name.name,
                    image = it.image[it.image.size - 1].label,
                    currency = it.price.attributes.currency,
                    amount = it.price.attributes.amount,
                    artist = it.artist.label
                )
            )
            Timber.e("DB Insert")
        }
    }

    fun cancel() {
        job.cancel()
    }
}