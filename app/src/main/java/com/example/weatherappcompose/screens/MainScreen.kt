package com.example.weatherappcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappcompose.Constants.API_KEY
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.theme.BlueJC
import com.example.weatherappcompose.ui.theme.DarkBlueJC

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: ScreenViewModel = viewModel()
    var city by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val weatherdata by viewModel.weatherState.collectAsState()
    val scrollState = rememberScrollState() // Initialize scroll state here

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Weather App",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "More options"
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false } // Correct dismissal
                    ) {
                        viewModel.languagesMap.forEach {(langName,langCode)->
                            DropdownMenuItem(
                                text = { Text(langName) },
                                onClick = {
                                    viewModel.fetchWeather(city, API_KEY, langCode) // Use the selected language code
                                    expanded = false // Dismiss the menu after selection
                                }
                            )
                        }
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState), // Enables scrolling if content overflows
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp) // Adjust height as needed
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.White)
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.moutains),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                ) {
                    // TextField below the image and gradient
                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(30.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedLabelColor = Color.Gray,
                            focusedIndicatorColor = BlueJC,
                            focusedLabelColor = DarkBlueJC,
                            unfocusedIndicatorColor = BlueJC
                        )
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Button to fetch weather data
            Button(
                onClick = { viewModel.fetchWeather(city, API_KEY) },
                colors = ButtonDefaults.buttonColors(BlueJC)
            ) {
                Text("Check Weather")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display weather data
            val  response = weatherdata?.let {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        weatherCard(
                            label = "City",
                            value = it.name,
                            icon = Icons.Default.Place
                        )
                        weatherCard(
                            label = "Temperature",
                            value = "${it.main.temp}°C",
                            icon = Icons.Default.Star
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        weatherCard(
                            label = "Humidity",
                            value = "${it.main.humidity}%",
                            icon = Icons.Default.Warning
                        )
                        weatherCard(
                            label = "Description",
                            value = it.weather[0].description,
                            icon = Icons.Default.Info
                        )
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
