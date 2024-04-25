package ru.smak.dbtest313.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Group::class, Student::class],
    version = 1,
    exportSchema = false,
)
abstract class StudentsDatabase : RoomDatabase(){
    abstract fun getGroupsDao(): GroupsDao
    abstract fun getStudentsDao(): StudentsDao
}