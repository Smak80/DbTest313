package ru.smak.dbtest313.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentsDao {
    @Insert(entity = Student::class)
    suspend fun addStudent(student: Student): Long

    @Query("SELECT * FROM student WHERE id_group = :idGroup")
    fun getStudents(idGroup: Long): Flow<List<Student>>
}