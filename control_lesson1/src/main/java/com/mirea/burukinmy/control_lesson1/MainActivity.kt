package com.mirea.burukinmy.control_lesson1

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ContactScreen(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    var name by remember { mutableStateOf("Tony Stark") }
    var organisation by remember { mutableStateOf("Mirea") }
    var isFavorite by remember { mutableStateOf(false) }

    if (isLandscape) {
        // Альбомная ориентация: фото слева, данные справа
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Фото контакта
            ContactPhoto(
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxHeight()
            )
            Spacer(modifier = Modifier.width(16.dp))
            // Данные контакта
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                ContactDetails(
                    name = name,
                    onNameChange = { name = it },
                    organisation = organisation,
                    onOrgChange = { organisation = it },
                    isFavorite = isFavorite,
                    onFavoriteChange = { isFavorite = it }
                )
            }
        }
    } else {
        // Портретная ориентация: фото сверху, данные снизу
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            ContactPhoto(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                ContactDetails(
                    name = name,
                    onNameChange = { name = it },
                    organisation = organisation,
                    onOrgChange = { organisation = it },
                    isFavorite = isFavorite,
                    onFavoriteChange = { isFavorite = it }
                )
            }
        }
    }
}

@Composable
fun ContactPhoto(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(Color(0xFFE0E0E0)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Contact photo",
            modifier = Modifier.size(100.dp),
            tint = Color.Gray
        )
    }
}

@Composable
fun ContactDetails(
    name: String,
    onNameChange: (String) -> Unit,
    organisation: String,
    onOrgChange: (String) -> Unit,
    isFavorite: Boolean,
    onFavoriteChange: (Boolean) -> Unit
) {
    // Имя
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Name") },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))

    // Организация
    OutlinedTextField(
        value = organisation,
        onValueChange = onOrgChange,
        label = { Text("Organisation") },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(16.dp))

    // Телефон
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "Call",
                tint = Color(0xFF4CAF50)
            )
        }
        Column {
            Text("+7 (911)111-11-11", fontSize = 16.sp)
            Text("(Mobile)", fontSize = 12.sp, color = Color.Gray)
        }
    }
    Spacer(modifier = Modifier.height(8.dp))

    // Чекбокс "Favorite"
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = isFavorite, onCheckedChange = onFavoriteChange)
        Text("Favorite")
    }
    Spacer(modifier = Modifier.height(16.dp))

    // Кнопка Save
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Button(onClick = {}) {
            Text("SAVE")
        }
    }
}
