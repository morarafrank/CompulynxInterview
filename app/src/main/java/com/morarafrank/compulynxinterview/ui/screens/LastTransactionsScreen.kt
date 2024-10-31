package com.morarafrank.compulynxinterview.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morarafrank.compulynxinterview.R
import com.morarafrank.compulynxinterview.ui.composables.SingleTransactionRow
import com.morarafrank.compulynxinterview.ui.theme.fontFamily

//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LastTransactionsScreen(
    navigateBack: () -> Unit,
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
                            text = stringResource(id = R.string.last_transactions),
                            fontSize = 18.sp,
                            fontFamily = fontFamily
                        )
                    }
                },
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
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ){


                Text(
                    text = "LAST 100 TRANSACTIONS",
                    fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
                    modifier = modifier.padding(4.dp),
                    fontSize = 20.sp
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {

                    item {
                        SingleTransactionRow(
                            transactionId = "Account To",
                            amount = "Amount",
                            fontFamily = FontFamily(Font(R.font.dm_sans_bold))
                        )
                    }
                    items(13){
                        SingleTransactionRow(
                            transactionId = "ACT001",
                            amount = "1000",
                            fontFamily = fontFamily
                        )
                    }
                    item {
                        SingleTransactionRow(
                            transactionId = "Total",
                            amount = "8000",
                            fontFamily = FontFamily(Font(R.font.dm_sans_bold))
                        )
                    }
                }
            }
        }
    )
}