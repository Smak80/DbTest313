package ru.smak.dbtest313

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import ru.smak.dbtest313.database.StudentsDatabase

class MainViewModel(app: Application) : AndroidViewModel(app){
    private val db: StudentsDatabase by lazy {
        Room.databaseBuilder(
            app.applicationContext,
            StudentsDatabase::class.java,
            "students_db"
        ).build()
    }
}