package ru.smak.dbtest313.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group")
data class Group(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String,
)
