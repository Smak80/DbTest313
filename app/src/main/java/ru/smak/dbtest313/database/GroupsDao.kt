package ru.smak.dbtest313.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupsDao {
    @Insert(entity = Group::class)
    suspend fun addGroup(group: Group): Long

    @Delete(entity = Group::class)
    suspend fun removeGroup(group: Group)

    @Query("SELECT * FROM `group`")
    fun getAllGroups(): Flow<List<Group>>
}