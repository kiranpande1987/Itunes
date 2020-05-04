package com.kprights.itunes.app.viewmodel

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kprights.itunes.app.common.DatabaseService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
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
    @Test
    fun saveEntry_saveToLocal() {

        val database =
            DatabaseService.getInstance(ApplicationProvider.getApplicationContext()).entryDao
        val repository: AppRepository = AppRepository(database = database)

        Assert.assertEquals(repository.dbEntries.value.isNullOrEmpty(), true)
        Assert.assertEquals(AppRepository.ApiStatus.LOADING, repository.status.value)
    }
}