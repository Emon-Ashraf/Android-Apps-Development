package com.example.rainbowapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rainbowapp.ui.theme.RainbowAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RainbowAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RainbowApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RainbowApp() {
    val selectedColor = remember { mutableStateOf<Int?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Select your favorite color!",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .height(56.dp)
                            .padding(top = 12.dp),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(rainbows) { rainbow ->
                    RainbowItemsCard(
                        rainbow = rainbow,
                        onClick = { selectedColor.value = rainbow.colorName }
                    )
                }
            }

            // Show the alert dialog when a color is selected
            selectedColor.value?.let { colorResId ->
                AlertDialog(
                    onDismissRequest = {
                        selectedColor.value = null
                    },
                    title = {
                        Text(text = "Your favorite color is")
                    },
                    text = {
                        Text(
                            text = " ${stringResource(id = colorResId)}!!!",
                            textAlign = TextAlign.Center
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                selectedColor.value = null
                            },
                            colors = ButtonDefaults.buttonColors(Color.Transparent)
                        ) {
                            Text(text = "OK",modifier = Modifier, color = Color(0xFF6A5ACD) )
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                selectedColor.value = null
                            },
                            colors = ButtonDefaults.buttonColors(Color.Transparent)
                        ) {
                            Text(text = "Dismiss", modifier = Modifier, color = Color(0xFF6A5ACD))
                        }
                    }

                )
            }


        }
    }
}


@Composable
fun RainbowItemsCard(rainbow: Rainbow, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color(0xFFFFF0F5))
        ) {
            Text(
                text = stringResource(id = rainbow.colorName),
                modifier = Modifier
                    .padding(start = 20.dp)
                    .weight(1f),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(rainbow.colorValue)
            ) {

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RainbowAppTheme {
        RainbowApp()
    }
}