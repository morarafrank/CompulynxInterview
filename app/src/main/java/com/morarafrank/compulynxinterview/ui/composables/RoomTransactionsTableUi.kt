package com.morarafrank.compulynxinterview.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morarafrank.compulynxinterview.R
import com.morarafrank.compulynxinterview.data.local.transaction.LocalTransaction
import com.morarafrank.compulynxinterview.data.remote.model.Transaction
import com.morarafrank.compulynxinterview.ui.theme.fontFamily
import com.morarafrank.compulynxinterview.ui.theme.mediumFont

@Composable
fun RoomTransactionsTableUi(
    modifier: Modifier = Modifier,
    transactions: List<LocalTransaction>
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {

        item{
            Row (
                modifier = modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "LOCAL TRANSACTIONS",
                    fontFamily = mediumFont,
                    fontSize = 20.sp,
                )
            }
        }
        item {
            SingleTransactionRow(
                transactionId = "Account To",
                amount = "Amount",
                fontFamily = FontFamily(Font(R.font.dm_sans_bold))
            )
        }

        items(transactions){ transaction ->
            SingleTransactionRow(
                transactionId = transaction.accountTo,
                amount = transaction.amount.toString(),
                fontFamily = fontFamily
            )
        }
        item {
            SingleTransactionRow(
                transactionId = "Total",
                amount = transactions.sumOf { transaction ->
                    transaction.amount
                }.toString(),
                fontFamily = FontFamily(Font(R.font.dm_sans_bold))
            )
        }
    }

}