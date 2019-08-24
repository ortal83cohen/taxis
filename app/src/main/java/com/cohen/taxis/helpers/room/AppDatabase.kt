package com.cohen.taxis.helpers.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cohen.taxis.model.Taxi

@Database(
    entities = [(Taxi::class)],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taxiDao(): TaxiDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "room-database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as AppDatabase
        }

    }
}