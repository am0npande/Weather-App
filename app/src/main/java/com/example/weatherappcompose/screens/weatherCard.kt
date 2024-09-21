package com.example.weatherappcompose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappcompose.ui.theme.BlueJC
import com.example.weatherappcompose.ui.theme.DarkBlueJC

@Composable
fun weatherCard(
    label: String, value: String, icon: ImageVector,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp) // Use a fixed width
            .height(180.dp), // Use a fixed height
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ){
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = DarkBlueJC,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(maxLines = 1,text = label, fontSize = 14.sp, color = DarkBlueJC)
            }
            Spacer(Modifier.height(4.dp))
            Box(
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = value,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = BlueJC
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeatherCards() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            weatherCard(
                label = "City",
                value = "Delhi",
                icon = Icons.Default.Place
            )
            weatherCard(
                label = "Temperature",
                value = "43°C",
                icon = Icons.Default.Star
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            weatherCard(
                label = "Humidity",
                value = "67%",
                icon = Icons.Default.Warning
            )
            weatherCard(
                label = "Pressure",
                value = "1013 hPa",
                icon = Icons.Default.Info
            )
        }
    }
}
