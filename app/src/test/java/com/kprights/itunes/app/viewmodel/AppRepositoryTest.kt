package com.kprights.itunes.app.viewmodel

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kprights.itunes.app.common.DatabaseService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Copyright (c) 2020 for KPrights
 *
 *
 * User : Kiran Pande
 * Date : 04/05/20
 * Time : 7:21 PM
 */

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class AppRepositoryTest {
//    @Test
//    fun saveEntry_saveToLocal() {
//
//        val database =
//            DatabaseService.getInstance(ApplicationProvider.getApplicationContext()).entryDao
//        val repository: AppRepository = AppRepository(database = database)
//
//        Assert.assertEquals(repository.dbEntries.value.isNullOrEmpty(), true)
//        Assert.assertEquals(AppRepository.ApiStatus.LOADING, repository.status.value)
//    }


    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testAppRepository() = runBlocking {

        val database =
            DatabaseService.getInstance(ApplicationProvider.getApplicationContext()).entryDao
        val repository = AppRepository(database, testCoroutineDispatcher)

        val baseModel = repository.fetchDataFromRemote()

        val category = baseModel?.feed?.entry?.groupingBy { it.category.attributes.label }

        println(category?.eachCount())

        println(baseModel?.feed?.entry?.filter { it.name.name.startsWith("a", true) }?.size)
        println(baseModel?.feed?.entry?.filter { it.name.name.startsWith("b", true) }?.size)
        println(baseModel?.feed?.entry?.filter { it.name.name.startsWith("c", true) }?.size)
        println(baseModel?.feed?.entry?.filter { it.name.name.startsWith("d", true) }?.size)

        Assert.assertEquals(true, (baseModel != null))
        Assert.assertEquals(false, baseModel?.feed?.entry?.isEmpty())

        repository.deleteDataFromDatabase()

        repository.insertDataIntoDatabase(baseModel!!)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tear() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}