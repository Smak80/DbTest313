package ru.smak.dbtest313.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GroupsDao {
    @Insert(entity = Group::class)
    fun addGroup(group: Group): Long

    @Delete(entity = Group::class)
    fun removeGroup(group: Group)

    @Query("SELECT * FROM `group`")
    fun getAllGroups(): List<Group>
}