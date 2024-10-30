package com.morarafrank.compulynxinterview.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morarafrank.compulynxinterview.R
import com.morarafrank.compulynxinterview.ui.theme.fontFamily

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMoneyScreen(
//    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.send_money_page),
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.dm_sans_medium))
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
//                        navigateBack()
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
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                var accountTo by remember{ mutableStateOf("") }
                var amount by remember{ mutableStateOf("") }

                Text(
                    text = "SEND MONEY",
                    fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
                    modifier = modifier.padding(4.dp),
                    fontSize = 20.sp
                )

                TextField(
                    value = accountTo,
                    onValueChange = {
                        accountTo = it
                    },
                    modifier = modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Account To", fontFamily = fontFamily)
                    }
                )

                TextField(
                    value = amount,
                    onValueChange = {
                        amount = it
                    },
                    modifier = modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Amount", fontFamily = fontFamily)
                    }
                )

                Button(
                    onClick = { /*TODO*/ },
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Send",
                        modifier = modifier.padding(4.dp)
                    )
                }


                
            }
        }
    )
}