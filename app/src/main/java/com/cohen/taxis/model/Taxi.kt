package com.cohen.taxis.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "taxi")
data class Taxi constructor(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var title: String? = null, var eta: Int = 0
)