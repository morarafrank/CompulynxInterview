package com.morarafrank.compulynxinterview.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morarafrank.compulynxinterview.R
import com.morarafrank.compulynxinterview.ui.theme.fontFamily

//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerProfileScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = {
                        navigateBack()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back IconButton")
                    }
                }
            )
        },
        content = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ){


                Text(
                    text = "CUSTOMER PROFILE",
                    fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
                    fontSize = 20.sp
                )
                Spacer(modifier = modifier.height(20.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Customer Name: ",
                        fontFamily = fontFamily,
                    )
                    Text(
                        text = "John Doe",
                        fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
                        fontSize = 16.sp
                    )
                }

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Customer ID: ",
                        fontFamily = fontFamily,
                    )
                    Text(
                        text = "156757585",
                        fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
                        fontSize = 16.sp
                    )
                }
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Customer Account: ",
                        fontFamily = fontFamily,
                    )
                    Text(
                        text = "uy897695",
                        fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
                        fontSize = 16.sp
                    )
                }
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Customer Email: ",
                        fontFamily = fontFamily,
                    )
                    Text(
                        text = "johndoe@gmail.com",
                        fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
                        fontSize = 16.sp
                    )
                }


                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        navigateBack()
                    },
                    modifier = modifier.fillMaxWidth(0.5f)
                ) {
                    Text(
                        text = "Home Page",
                        fontFamily = fontFamily
//                        modifier = modifier.padding(4.dp)
                    )
                }
            }
        }
    )
}