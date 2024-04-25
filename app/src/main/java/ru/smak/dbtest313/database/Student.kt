package ru.smak.dbtest313.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "student",
    foreignKeys = [
        ForeignKey(
            entity = Group::class,
            parentColumns = ["id"],
            childColumns = ["id_group"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Student(
    var id: Long,
    @ColumnInfo(name = "id_group")
    var idGroup: Long,
    @ColumnInfo(name = "full_name")
    var FullName: String,
)