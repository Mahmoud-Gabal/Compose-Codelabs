package com.example.mystatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier

@Composable
fun WellnessScreen(modifier: Modifier = Modifier){
    Column {
        val list = remember{
            taskList().toMutableStateList()
        }
        StatefullCounter(modifier,onAdd = {list.add(listy("Task ${list.size + 1}",
            list.size +1
        ))})
        WellnessTaskList(list,onClose = {item -> list.remove(item)})
    }
}

fun taskList () = List(20){i -> listy("Task ${i+1}",i+1)}