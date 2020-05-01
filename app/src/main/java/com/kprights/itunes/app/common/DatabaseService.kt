package com.kprights.itunes.app.common

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.kprights.itunes.app.model.DBEntry


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 11/04/20
 * Time : 2:09 AM
 */

@Database(entities = [DBEntry::class], version = 1, exportSchema = false)
abstract class DatabaseService : RoomDatabase() {
    abstract val entryDao: EntryDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseService? = null

        fun getInstance(context: Context): DatabaseService {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseService::class.java,
                    "apple_database"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
            }

            return instance
        }
    }
}

@Dao
interface EntryDao {
    @Insert
    fun insert(entry: DBEntry)

    @Query("SELECT * FROM DBEntry")
    fun getAllEntries(): LiveData<List<DBEntry>>

    @Query("Delete from DBEntry")
    fun deleteAllEntries()
}
