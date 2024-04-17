package com.example.mystatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun StatelessCounter(modifier: Modifier = Modifier,onAdd: () -> Unit,count : Int,onIncrement : () -> Unit) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = {
            onIncrement()
            onAdd()
                         }, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}
@Composable
fun StatefullCounter(modifier: Modifier = Modifier,onAdd : () -> Unit){
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(count = count, onAdd = onAdd) {
        count++
    }
}
//@Composable
//fun WaterCounter(modifier: Modifier = Modifier){
//    var count by remember {
//        mutableStateOf(0)
//    }
//
//    Column (modifier = modifier.padding(16.dp)){
//        if (count > 0) {
//            var showTask by remember {
//                mutableStateOf(true)
//            }
//            if (showTask){
//                WellnessTaskItem(taskName = "Have you taken your 15 minute walk today?",
//                    onClose = { showTask = false})
//            }
//            Text(
//                text = "You've had $count glasses"
//            )
//        }
//        Row (Modifier.padding(top = 8.dp)){
//            Button(
//                onClick = { count++ },
//                enabled = count < 10
//            ) {
//                Text(text = "Add more")
//            }
//            Button(
//                onClick = { count = 0 },
//                Modifier.padding(start = 8.dp)) {
//                Text("Clear water count")
//            }
//        }
//    }
//}