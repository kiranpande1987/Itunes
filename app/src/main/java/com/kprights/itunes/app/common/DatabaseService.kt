package com.kprights.itunes.app.common

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.kprights.itunes.app.model.BaseModel
import com.kprights.itunes.app.model.Convertors


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 11/04/20
 * Time : 2:09 AM
 */

@Database(entities = [BaseModel::class], version = 1, exportSchema = false)
@TypeConverters(Convertors::class)
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
    suspend fun insert(baseModel: BaseModel)

    @Query("SELECT * FROM BaseModel")
    fun getAllEntries(): LiveData<BaseModel>

    @Query("Delete from BaseModel")
    suspend fun deleteAllEntries()
}
