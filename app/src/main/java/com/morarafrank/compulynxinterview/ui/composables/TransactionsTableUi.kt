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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morarafrank.compulynxinterview.R
import com.morarafrank.compulynxinterview.data.remote.model.Transaction
import com.morarafrank.compulynxinterview.ui.theme.boldFont
import com.morarafrank.compulynxinterview.ui.theme.fontFamily
import com.morarafrank.compulynxinterview.ui.theme.mediumFont
import kotlin.random.Random

@Composable
fun TransactionsTableUi(
    modifier: Modifier = Modifier,
    transactions: List<Transaction>
) {

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {

        item{
            Row (
                modifier = modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "LAST 100 TRANSACTIONS",
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
                transactionId = transaction.transactionId,
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

fun generateRandomTransaction(id: Int): Transaction {
    // Generate random data for transaction fields
    val transactionId = "TXN-${Random.nextInt(1000, 9999)}"
    val customerId = "CUST-${Random.nextInt(100, 999)}"
    val accountNo = "ACC-${Random.nextInt(100000, 999999)}"
    val amount = String.format("%.1f", Random.nextDouble(10.0, 1000.0))
    val balance = String.format("%.1f", Random.nextDouble(1000.0, 10000.0))
    val transactionType = if (Random.nextBoolean()) "Deposit" else "Withdrawal"
    val debitOrCredit = if (transactionType == "Deposit") "Credit" else "Debit"

    return Transaction(
        id = id,
        transactionId = transactionId,
        customerId = customerId,
        accountNo = accountNo,
        amount = amount.toDouble(),  // Convert back to Double
        balance = balance.toDouble(),  // Convert back to Double
        transactionType = transactionType,
        debitOrCredit = debitOrCredit
    )
}


fun generateTransactionList(): List<Transaction> {
    return List(20) { id -> generateRandomTransaction(id + 1) }
}
//@Preview(showBackground = true)
@Composable
private fun PrevTransTable() {
    TransactionsTableUi(
       transactions = generateTransactionList()
    )
}

