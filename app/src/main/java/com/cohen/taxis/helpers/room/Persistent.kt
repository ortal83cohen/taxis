package com.cohen.taxis.helpers.room

import android.content.Context
import androidx.lifecycle.LiveData
import com.cohen.taxis.model.Taxi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Persistent(context: Context) : IPersistent {

    private val appDatabase: AppDatabase = AppDatabase.getAppDatabase(context)

    override suspend fun liveTaxis(): LiveData<List<Taxi>> {
        return withContext(Dispatchers.IO) {
            appDatabase.taxiDao().liveTaxis
        }
    }

    override suspend fun taxis(): List<Taxi> {
        return withContext(Dispatchers.IO) {
            appDatabase.taxiDao().getAll()
        }
    }

    override suspend fun addOrUpdateTaxi(taxi: Taxi) {
        withContext(Dispatchers.IO) {
            appDatabase.taxiDao().insert(taxi)
        }
    }

    override suspend fun addOrUpdateTaxis(taxi: List<Taxi>) {
        withContext(Dispatchers.IO) {
            appDatabase.taxiDao().insertAll(taxi)
        }
    }

}
