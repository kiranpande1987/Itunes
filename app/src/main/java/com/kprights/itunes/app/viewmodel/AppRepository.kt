package com.kprights.itunes.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kprights.itunes.app.common.EntryDao
import com.kprights.itunes.app.model.BaseModel
import kotlinx.coroutines.*


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/04/20
 * Time : 11:00 PM
 */

class AppRepository(database: EntryDao, val dispatcher: CoroutineDispatcher) {

    enum class ApiStatus { LOADING, ERROR, DONE, OFFLINE }

    private val remoteDataSource = RemoteDataSource()
    private val localDataSource = LocalDataSource(database)

    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    val status: MutableLiveData<ApiStatus> = MutableLiveData<ApiStatus>()
    val dbEntries: LiveData<BaseModel> = localDataSource.getAllEntries()

    init {
        updateDataFromRemoteDataSource()
    }

    // ----------------------------------------------------------------------------
    // Fetch Latest Data from Web.
    // if Success, Delete Data from Local Database.
    // Save Latest Data to Local Database.
    // If Error, Throw Exception.
    fun updateDataFromRemoteDataSource() {
        scope.launch(dispatcher) {
            val baseModel = fetchDataFromRemote()

            baseModel?.let {
                deleteDataFromDatabase()
                insertDataIntoDatabase(it)
                status.value = ApiStatus.DONE
            }
        }
    }

    suspend fun fetchDataFromRemote(): BaseModel? {
        try {
            status.value = ApiStatus.LOADING
            return remoteDataSource.getFeed()
        } catch (e: Exception) {
            status.value = ApiStatus.ERROR
        }

        return null
    }

    suspend fun deleteDataFromDatabase() {
        withContext(dispatcher)
        {
            localDataSource.deleteAllEntries()
        }
    }

    suspend fun insertDataIntoDatabase(baseModel: BaseModel) {
        withContext(dispatcher)
        {
            baseModel.feed.entry.forEach {
                localDataSource.saveEntries(
                    baseModel
//                    DBEntry().copy(
//                        name = it.name.name,
//                        image = it.image[it.image.size - 1].label,
//                        currency = it.price.attributes.currency,
//                        amount = it.price.attributes.amount,
//                        artist = it.artist.label
//                    )
                )
            }
        }
    }

    fun cancel() {
        job.cancel()
    }

    fun refreshData() {
        updateDataFromRemoteDataSource()
    }
}

object SingleTon