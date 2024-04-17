package com.example.basiccodelab

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiccodelab.ui.theme.BasicCodeLabTheme
import java.util.Collections.list

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicCodeLabTheme {
                Myapp()
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        var isExpanded by rememberSaveable {
            mutableStateOf(false)
        }
        val extraPadding by animateDpAsState(
            targetValue = if (isExpanded) 80.dp else 10.dp,
            animationSpec = tween(
                durationMillis = 500
            )
        )
        Surface(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            , color = MaterialTheme.colorScheme.primary) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(12.dp)
            ) {
                Column(modifier = Modifier.padding
                    (start = 10.dp, end = 10.dp, top = 10.dp, bottom = extraPadding))
                {
                    Text(
                        text = "Hello,"

                    )
                    Text(
                        text = name,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                IconButton(onClick = { isExpanded = !isExpanded }
                    , Modifier.padding(10.dp)) {
                    Icon(
                        imageVector = if(isExpanded) Filled.ExpandLess else Filled.ExpandMore
                        , contentDescription = if (isExpanded) "Show less" else "Show more"
                    )
                }
            }
        }
    }

    @Composable
    fun Board(names: List<String> = List(20) { "$it" },onClicked: () -> Unit) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)) {
            ElevatedButton(onClick = onClicked,Modifier.padding(start = 8.dp, top = 4.dp),
                colors = ButtonDefaults.elevatedButtonColors(containerColor =
                MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary)){
                Text(text = "Back")
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(names) { item ->
                    Greeting(name = item)
                }
            }
        }
    }
    @Composable
    fun Myapp() {
        var shouldShowHome by rememberSaveable {
            mutableStateOf(true)
        }
        if (shouldShowHome){
            home({shouldShowHome = false})
        }else {
            Board(onClicked = {shouldShowHome= true})
        }
    }
    @Composable
    fun home(onClicked : () -> Unit){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Welcome to our Basic Code lab", color = if (isSystemInDarkTheme())MaterialTheme.colorScheme.onPrimary else Color.White)
            Spacer(modifier = Modifier.height(15.dp))
            ElevatedButton(onClick = onClicked,
                colors = ButtonDefaults.elevatedButtonColors(containerColor =
                MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary)) {
                Text(text = "Continue")
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview1() {
        BasicCodeLabTheme()
        {
            home({})
        }
    }
    @Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
    @Composable
    fun GreetingPreview2() {
        BasicCodeLabTheme()
        {
           Board (onClicked = {})
        }
    }
}
