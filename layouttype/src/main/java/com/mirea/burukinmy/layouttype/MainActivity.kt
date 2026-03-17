package com.mirea.burukinmy.layouttype

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LayoutTypeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LayoutTypeScreen(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Аналог LinearLayout (vertical + horizontal)
        Text(
            text = "LinearLayout (Column + Row)",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LinearLayoutDemo()

        Spacer(modifier = Modifier.height(24.dp))

        // Аналог TableLayout
        Text(
            text = "TableLayout (Row grid)",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        TableLayoutDemo()

        Spacer(modifier = Modifier.height(24.dp))

        // Activity Second — 6 кнопок с адаптацией под ориентацию
        Text(
            text = "Activity Second (${if (isLandscape) "Landscape" else "Portrait"})",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        ActivitySecondDemo(isLandscape)
    }
}

@Composable
fun LinearLayoutDemo() {
    // Аналог LinearLayout vertical с двумя горизонтальными рядами
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray)
            .padding(8.dp)
    ) {
        // Первый горизонтальный ряд (аналог LinearLayout horizontal)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("BUTTON") }
            Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("BUTTON") }
            Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("BUTTON") }
        }
        Spacer(modifier = Modifier.height(4.dp))
        // Второй горизонтальный ряд
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("BUTTON") }
            Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("BUTTON") }
            Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("BUTTON") }
        }
    }
}

@Composable
fun TableLayoutDemo() {
    // Аналог TableLayout — строки с разным количеством элементов
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray)
            .padding(8.dp)
    ) {
        // Строка 1: Button, TextView, Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("BTN") }
            Text(
                text = "This is Table View!",
                modifier = Modifier.weight(1f),
                fontSize = 14.sp
            )
            Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("BTN") }
        }
        Spacer(modifier = Modifier.height(4.dp))
        // Строка 2: Button, Checkbox
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("BTN") }
            var checked by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = checked, onCheckedChange = { checked = it })
                Text("CheckBox")
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        // Строка 3: IconButton, Button, Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            IconButton(onClick = {}, modifier = Modifier.weight(1f)) {
                Text("▶", fontSize = 24.sp)
            }
            Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("BTN") }
            Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("BTN") }
        }
    }
}

@Composable
fun ActivitySecondDemo(isLandscape: Boolean) {
    var text by remember { mutableStateOf("New life for mirea activity!") }
    val buttons = listOf(
        "ПЕРВАЯ КНОПКА", "ВТОРАЯ КНОПКА", "ТРЕТЬЯ КНОПКА",
        "ЧЕТВЁРТАЯ КНОПКА", "ПЯТАЯ КНОПКА", "ШЕСТАЯ КНОПКА"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray)
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Message") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (isLandscape) {
            // Альбомная: кнопки по 2 в ряд (аналог TableLayout)
            for (i in buttons.indices step 2) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Button(onClick = {}, modifier = Modifier.weight(1f)) {
                        Text(buttons[i], fontSize = 12.sp)
                    }
                    if (i + 1 < buttons.size) {
                        Button(onClick = {}, modifier = Modifier.weight(1f)) {
                            Text(buttons[i + 1], fontSize = 12.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        } else {
            // Портретная: кнопки в столбик
            buttons.forEach { label ->
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}
