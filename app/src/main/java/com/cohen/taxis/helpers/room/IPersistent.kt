package com.cohen.taxis.helpers.room

import androidx.lifecycle.LiveData
import com.cohen.taxis.model.Taxi

interface IPersistent {

    suspend fun liveTaxis(): LiveData<List<Taxi>>

    suspend fun taxis(): List<Taxi>

    suspend fun addOrUpdateTaxi(taxi: Taxi)

    suspend fun addOrUpdateTaxis(taxi: List<Taxi>)

}
