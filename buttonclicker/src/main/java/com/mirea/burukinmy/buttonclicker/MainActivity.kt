package com.mirea.burukinmy.buttonclicker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ButtonClickerScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ButtonClickerScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var displayText by remember { mutableStateOf("Hello World!") }
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // TextView (tvOut)
        Text(
            text = displayText,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // CheckBox
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
            Text("Активен")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопки
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            // Кнопка "WHO AM I?" — аналог первого способа (setOnClickListener)
            Button(onClick = {
                displayText = "Мой номер по списку № 5"
                isChecked = true
            }) {
                Text("WHO AM I?")
            }

            // Кнопка "IT IS NOT ME" — аналог второго способа (onClick в XML)
            Button(onClick = {
                displayText = "Это не я сделал"
                isChecked = false
                Toast.makeText(context, "Ещё один способ!", Toast.LENGTH_SHORT).show()
            }) {
                Text("IT IS NOT ME")
            }
        }
    }
}
