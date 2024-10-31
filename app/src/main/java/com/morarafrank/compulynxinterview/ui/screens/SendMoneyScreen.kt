package com.morarafrank.compulynxinterview.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.morarafrank.compulynxinterview.R
import com.morarafrank.compulynxinterview.data.local.transaction.Transaction
import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyBody
import com.morarafrank.compulynxinterview.ui.composables.TransactionSuccessfulAlertDialog
import com.morarafrank.compulynxinterview.ui.theme.fontFamily
import com.morarafrank.compulynxinterview.ui.viewmodel.CompulynxViewModel
import com.morarafrank.compulynxinterview.ui.viewmodel.UiState
import com.morarafrank.compulynxinterview.utils.UiUtils
import kotlinx.coroutines.delay

//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMoneyScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CompulynxViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var showSuccessDialog by remember{ mutableStateOf(false) }

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
                    onClick = {
                        viewModel.sendMoney(
                            sendMoneyBody = SendMoneyBody(
                                accountTo = accountTo,
                                amount = amount
                            ),
                            sendTransaction = Transaction(
                                id = 0,
                                customerID = "1",
                                customerAccount = "1",
                                accountTo = accountTo,
                                amount = amount,
                                status = "Sent"
                            )
                        )
                    },
                    modifier = modifier.fillMaxWidth()
                ) {
                    when(uiState){
                        is UiState.Idle -> {
                            Text(
                                text = "Send",
                                modifier = modifier.padding(4.dp),
                                fontFamily = fontFamily
                            )
                        }
                        is UiState.Loading -> {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        is UiState.Success -> {
                            Text(
                                text = "Transaction Successful",
                                fontFamily = fontFamily
                            )
                            showSuccessDialog = true
//                            LaunchedEffect(Unit) {
//                                delay(2000)
//                                navigateBack()
//                            }
                        }
                        is UiState.Error -> {
                            Text(
                                text = "Transaction failed",
                                fontFamily = fontFamily
                            )
                            UiUtils.showToast("Transaction failed", context)
                            LaunchedEffect(Unit) {
                                delay(2000)
                                viewModel.resetUiState()
                            }
                        }
                    }
                }

                TransactionSuccessfulAlertDialog(
                    openDialog = showSuccessDialog,
                    closeDialog = {
                        showSuccessDialog = false
                        navigateBack()
                    },
                    navigateToHome = {
                        navigateBack()
                    }
                )
            }
        }
    )
}