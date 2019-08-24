package com.cohen.taxis.helpers.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cohen.taxis.model.Taxi


@Dao
interface TaxiDao {

    @get:Query("SELECT * FROM taxi order by taxi.eta")
    val liveTaxis: LiveData<List<Taxi>>

    @get:Query("SELECT * FROM taxi LIMIT 1")
    val taxi: Taxi

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(taxi: Taxi)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(taxi: List<Taxi>)

    @Delete
    fun delete(taxi: Taxi)

    @Query("DELETE FROM taxi")
    fun deleteAll()

    @Query("SELECT * FROM taxi WHERE id=:key LIMIT 1")
    operator fun get(key: String): Taxi

    @Query("SELECT * FROM taxi")
     fun getAll(): List<Taxi>

}

