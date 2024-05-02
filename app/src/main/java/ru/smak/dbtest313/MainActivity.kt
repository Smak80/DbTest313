package ru.smak.dbtest313

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.smak.dbtest313.database.Group
import ru.smak.dbtest313.database.Student
import ru.smak.dbtest313.ui.theme.DbTest313Theme

class MainActivity : ComponentActivity() {

    private val mvm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DbTest313Theme {
                val groups by mvm.groups.collectAsState(initial = listOf())
                Column(modifier = Modifier.fillMaxSize()) {
                    GroupsList(
                        groups,
                        mvm.selectedGroup,
                    ){
                        mvm.selectGroup(it)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = mvm.currentName,
                            onValueChange = { mvm.currentName = it },
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick = mvm::addStudent,
                            enabled = mvm.selectedGroup != null
                        ){
                            Text(stringResource(R.string.btn_add))
                        }
                    }
                    StudentsList(
                        mvm.studList,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun GroupCard(
    group: Group,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onSelectGroup: ()->Unit = {},
){
    ElevatedCard(
        onClick = { onSelectGroup() },
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Unspecified,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else Color.Black
        ),
    ) {
        Text(group.name, modifier = Modifier.padding(24.dp))
    }
}

@Composable
fun GroupsList(
    groups: List<Group>,
    selectedGroup: Group?,
    modifier: Modifier = Modifier,
    onSelectGroup: (Group)->Unit = {},
){
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(groups){
            GroupCard(
                group = it,
                isSelected = selectedGroup == it
            ){
                onSelectGroup(it)
            }
        }
    }
}

@Composable
fun StudentsList(
    students: List<Student>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(students){
            StudentCard(student = it, Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun StudentCard(
    student: Student,
    modifier: Modifier = Modifier,
){
    ElevatedCard(modifier) {
        Column (
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(text = student.fullName)
        }
    }
}