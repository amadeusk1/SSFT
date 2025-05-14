package com.example.ssft

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ssft.ui.theme.SSFTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SSFTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserProfileInput(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileInput(modifier: Modifier = Modifier) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var goalWeight by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    val goals = listOf("Cut", "Maintain", "Bulk")
    var selectedGoal by remember { mutableStateOf(goals[0]) }

    Column(modifier = modifier.padding(16.dp)) {
        TextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Body Weight (kg)") },
            modifier = Modifier.padding(vertical = 4.dp)
        )

        TextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (cm)") },
            modifier = Modifier.padding(vertical = 4.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedGoal,
                onValueChange = {},
                readOnly = true,
                label = { Text("Goal") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .menuAnchor()
                    .padding(vertical = 4.dp)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                goals.forEach { goal ->
                    DropdownMenuItem(
                        text = { Text(goal) },
                        onClick = {
                            selectedGoal = goal
                            expanded = false
                        }
                    )
                }
            }
        }

        TextField(
            value = goalWeight,
            onValueChange = { goalWeight = it },
            label = { Text("Goal Weight (kg)") },
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileInputPreview() {
    SSFTTheme {
        UserProfileInput()
    }
}
