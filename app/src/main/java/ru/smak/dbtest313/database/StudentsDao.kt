package ru.smak.dbtest313.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentsDao {
    @Insert(entity = Student::class)
    fun addStudent(student: Student): Long

    @Query("SELECT * FROM student WHERE id_group = :idGroup")
    fun getStudents(idGroup: Long): List<Student>
}