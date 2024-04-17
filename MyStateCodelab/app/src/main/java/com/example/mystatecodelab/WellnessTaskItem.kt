package com.example.mystatecodelab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@Composable
fun WellnessTaskItem(
    checked : Boolean,
    onCheckedChange : (Boolean) -> Unit,
    taskName : String,
    onClose : () -> Unit,
    modifier: Modifier = Modifier
){

    Row(modifier = modifier
        .fillMaxWidth()
        .padding(top = 4.dp)
        .background(Color.Magenta),
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = taskName,
            Modifier
                .weight(1f)
                .padding(start = 16.dp))
        Checkbox(checked = checked, onCheckedChange =  onCheckedChange )
        IconButton(onClick = onClose) {
            Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
        }
    }
}
@Composable
fun WellnessTaskItem(taskName: String,modifier: Modifier = Modifier,onClose : () -> Unit){
    var checkState by rememberSaveable {
        mutableStateOf(false)
    }
    WellnessTaskItem(
        checked = checkState,
        onCheckedChange = {new -> checkState = new},
        taskName = taskName,
        onClose = onClose)
}


