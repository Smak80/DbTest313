package ru.smak.dbtest313

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import ru.smak.dbtest313.database.Group
import ru.smak.dbtest313.database.GroupsDao
import ru.smak.dbtest313.database.Student
import ru.smak.dbtest313.database.StudentsDao
import ru.smak.dbtest313.database.StudentsDatabase

class MainViewModel(app: Application) : AndroidViewModel(app){

    var selectedGroup: Group? by mutableStateOf(null)

    var currentName by mutableStateOf("")

    private val db: StudentsDatabase by lazy {
        Room.databaseBuilder(
            app.applicationContext,
            StudentsDatabase::class.java,
            "students_db"
        ).build()
    }

    private val groupsDao: GroupsDao = db.getGroupsDao()
    private val studentsDao: StudentsDao = db.getStudentsDao()

    var studList = mutableStateListOf<Student>()
    private var studCollector: Job? = null

    val groups = groupsDao.getAllGroups()

    init{
        addGroup(Group(1, "05-313"))
        addGroup(Group(2, "05-314"))
        addGroup(Group(3, "05-312"))
//        groups.addAll(
//            getGroups()
//        )
    }

    fun addGroup(g: Group) = viewModelScope.launch(Dispatchers.IO){
        try {
            groupsDao.addGroup(g)
        } catch (_: Throwable){

        }
    }

    fun addStudent() = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (currentName.isNotBlank()) {
                selectedGroup?.let { selGroup ->
                    studentsDao.addStudent(
                        Student(
                            0,
                            idGroup = selGroup.id,
                            fullName = currentName,
                        )
                    )
                }
            }
        } catch (_: Throwable){

        }
    }

    fun selectGroup(group: Group) {
        selectedGroup = group.also { gr ->
            viewModelScope.launch(Dispatchers.IO) {
                studCollector?.apply {
                    cancelAndJoin()
                }
                studCollector = launch {
                    studentsDao.getStudents(gr.id).collect{
                        studList.clear()
                        studList.addAll(it)
                    }
                }
            }
        }
    }

    //fun getGroups() = groupsDao.getAllGroups()

}