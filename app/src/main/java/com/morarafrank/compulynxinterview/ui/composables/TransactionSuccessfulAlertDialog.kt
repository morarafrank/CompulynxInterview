package com.morarafrank.compulynxinterview.ui.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.morarafrank.compulynxinterview.ui.theme.fontFamily

@Composable
fun TransactionSuccessfulAlertDialog(
    openDialog: Boolean,
    closeDialog: () -> Unit,
    navigateToHome: () -> Unit
) {

    if (openDialog){
        AlertDialog(
            onDismissRequest = {
                closeDialog()
            },
            title = {
                Text(text = "Success!", fontFamily = fontFamily)
            },
            text = {
                Text(
                    text = "Transaction Successful!",
                    fontFamily = fontFamily
                )
            },
            confirmButton = {
                Button(onClick = {
                    navigateToHome()
                }) {
                    Text(text = "Ok", fontFamily = fontFamily)
                }
            }
        )
    }
}

//@Preview
//@Composable
//private fun PrevTransactionSuccessfulDialog() {
//    TransactionSuccessfulAlertDialog(
//        openDialog = true,
//        closeDialog = { /*TODO*/ },
//        navigateToHome = {}
//    )
//}