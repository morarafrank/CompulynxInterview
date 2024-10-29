package com.morarafrank.compulynxinterview.ui.screens

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.morarafrank.compulynxinterview.ui.theme.fontFamily

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
//    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
//    navigateToHome: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Login",
                        fontSize = 18.sp,
                        fontFamily = fontFamily
                    )
                }
            )
        },
        content = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var customerId by remember { mutableStateOf("") }
                var pin by remember { mutableStateOf("") }

                TextField(
                    value = customerId,
                    onValueChange = {
                        customerId = it
                    },
                    modifier = modifier.fillMaxWidth(),
                    placeholder = { Text(text = "Customer ID", fontFamily = fontFamily) }
                )

                TextField(
                    value = pin,
                    onValueChange = {
                        pin = it
                    },
                    modifier = modifier.fillMaxWidth(),
                    placeholder = { Text(text = "PIN", fontFamily = fontFamily) }
                )

                Button(
                    onClick = {
//                        navigateToHome()
                    },
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Login",
                        fontFamily = fontFamily,
                        modifier = modifier.padding(8.dp)
                    )
                }
            }
        }
    )
}