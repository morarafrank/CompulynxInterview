package com.morarafrank.compulynxinterview.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.morarafrank.compulynxinterview.R
import com.morarafrank.compulynxinterview.data.local.transaction.LocalTransaction
import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyBody
import com.morarafrank.compulynxinterview.ui.composables.TransactionSuccessfulAlertDialog
import com.morarafrank.compulynxinterview.ui.theme.fontFamily
import com.morarafrank.compulynxinterview.ui.viewmodel.CompulynxViewModel
import com.morarafrank.compulynxinterview.ui.viewmodel.UiState
import com.morarafrank.compulynxinterview.utils.CompulynxAndroidInterviewSharedPrefs
import com.morarafrank.compulynxinterview.utils.Resource
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

    val sendMoneyUiState by viewModel.sendMoneyUiState.collectAsState()
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
                        Icon(painter = painterResource(R.drawable.ic_arrow_back), contentDescription = "Back IconButton")
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

                var accountTo by rememberSaveable{ mutableStateOf("") }
                var amount by rememberSaveable{ mutableStateOf("") }

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
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )

                Button(
                    onClick = {
                        Log.d("SendMoneyScreen", "AccountTo: $accountTo, Amount: $amount")
                        val sendMoneyBody = SendMoneyBody(
                            accountTo = accountTo,
                            amount = amount.toInt(),
                            accountFrom = CompulynxAndroidInterviewSharedPrefs.getCustomerAccount(),
                            customerId = CompulynxAndroidInterviewSharedPrefs.getCustomerId()
                        )
                        val sendTransaction = LocalTransaction(
                            customerID = CompulynxAndroidInterviewSharedPrefs.getCustomerId(),
                            customerAccount = CompulynxAndroidInterviewSharedPrefs.getCustomerAccount(),
                            accountTo = accountTo,
                            amount = amount.toInt().toDouble(),
                            status = "Sent"
                        )
                        viewModel.sendMoney(
                            sendMoneyBody, sendTransaction
                        )

                        Log.i("SendMoneyScreen", "SendMoney: $sendMoneyBody  SendTransaction: $sendTransaction")
                    },
                    modifier = modifier.fillMaxWidth()
                ) {
                    when(sendMoneyUiState){

                        is Resource.Idle -> {
                            Text(
                                text = "Send",
                                modifier = modifier.padding(4.dp),
                                fontFamily = fontFamily
                            )
                        }

                        is Resource.Loading -> {
                            CircularProgressIndicator(
                                modifier = modifier.size(24.dp),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                        is Resource.Success -> {
                            Text(
                                text = "Transaction Successful",
                                fontFamily = fontFamily
                            )
                            showSuccessDialog = true
                            viewModel.resetUiState()
                        }
                        is Resource.Error -> {
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