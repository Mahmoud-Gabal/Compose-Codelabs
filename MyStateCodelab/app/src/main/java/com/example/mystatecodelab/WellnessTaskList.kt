package com.example.mystatecodelab

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.*
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList




@Composable
fun WellnessTaskList(taskList : SnapshotStateList<listy>,onClose : (listy) -> Unit){
    LazyColumn {
        items(taskList){item ->
            WellnessTaskItem(taskName = item.taskName,onClose = {onClose(item)})
        }
    }
}
