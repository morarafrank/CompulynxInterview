package com.morarafrank.compulynxinterview.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.morarafrank.compulynxinterview.R
import com.morarafrank.compulynxinterview.ui.theme.boldFont
import com.morarafrank.compulynxinterview.ui.theme.regularFont
import com.morarafrank.compulynxinterview.ui.viewmodel.CompulynxViewModel
import com.morarafrank.compulynxinterview.utils.CompulynxAndroidInterviewSharedPrefs
import com.morarafrank.compulynxinterview.utils.Resource
import kotlinx.coroutines.delay

//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    navigateToCustomerProfile: () -> Unit,
    navigateToLastTransactions: () -> Unit,
    navigateToSendMoney: () -> Unit,
    navigateToStatement: () -> Unit,
    viewModel: CompulynxViewModel = hiltViewModel()
) {

    val checkBalanceUiState by viewModel.checkBalanceUiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getCustomer()
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.home_page),
                            fontSize = 18.sp,
                            fontFamily = regularFont
                        )
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
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                var showBalance by remember{ mutableStateOf(false) }
//                var customerId by remember{ mutableStateOf("") }
//                var balance by rememberSaveable{ mutableStateOf(viewModel.balance) }
                var balance by rememberSaveable{ mutableStateOf("") }

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement
                        .spacedBy(4.dp, Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "WELCOME",
                        fontFamily = regularFont,
                        modifier = modifier.padding(4.dp),
                    )
                    Text(
                        text = viewModel.customer?.customerName.toString(),
                        fontFamily = boldFont
                    )
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    AnimatedVisibility(
                        visible = showBalance,
                        modifier = modifier.weight(1f)
                            .padding(8.dp)
                    ) {

                        Text(
                            text = balance,
                            fontFamily = regularFont,
                            modifier = modifier.padding(4.dp),
                            fontSize = 18.sp
                        )
                    }

                    AnimatedVisibility(
                        visible = !showBalance,
                        modifier = modifier.weight(1f)
                            .padding(8.dp)
                    ) {
                        Button(
                            onClick = {
                                viewModel.checkBalance()
                                showBalance = !showBalance
                            },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            when(checkBalanceUiState){
                                is Resource.Idle -> {
                                    Text(
                                        text = stringResource(id = R.string.balance),
                                        fontFamily = regularFont,
                                        modifier = modifier.padding(4.dp)
                                    )
                                }
                                is Resource.Loading -> {
                                    CircularProgressIndicator(
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                                is Resource.Success -> {
                                    balance = viewModel.balance
                                    LaunchedEffect(Unit) {
                                        delay(10000)
                                        viewModel.resetUiState()
                                    }
                                }

                                is Resource.Error -> {
                                    Text(
                                        text = stringResource(id = R.string.try_again),
                                        fontFamily = regularFont,
                                        modifier = modifier.padding(4.dp)
                                    )
                                    LaunchedEffect(Unit) {
                                        delay(5000)
                                        viewModel.resetUiState()
                                    }
                                }
                            }
                        }
                    }

                    Button(
                        onClick = {
                            navigateToSendMoney()
                        },
                        modifier = Modifier.weight(1f).padding(8.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.send_money),
                            fontFamily = regularFont,
                            fontSize = 12.sp,
                            modifier = modifier.padding(8.dp)
                        )
                    }
                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Button(
                        onClick = {
                            navigateToStatement()
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.view_statement),
                            fontFamily = regularFont,
                            fontSize = 12.sp,
                            modifier = modifier.padding(8.dp)
                        )
                    }

                    Button(
                        onClick = {
                            navigateToLastTransactions()
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.last_transactions),
                            fontFamily = regularFont,
                            fontSize = 12.sp,
                            modifier = modifier.padding(8.dp)
                        )
                    }

                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Button(
                        onClick = {
                            navigateToCustomerProfile()
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.profile),
                            fontFamily = regularFont,
                            fontSize = 12.sp,
                            modifier = modifier.padding(8.dp)
                        )
                    }

                    Button(
                        onClick = {

                            viewModel.logOut()

                            navigateBack()
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.logout),
                            fontFamily = regularFont,
                            fontSize = 12.sp,
                            modifier = modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    )
}

//@Preview
@Composable
private fun PreviewHome() {

    HomeScreen(
        navigateBack = {},
        navigateToCustomerProfile = {},
        navigateToStatement = {},
        navigateToLastTransactions = {},
        navigateToSendMoney = {},
    )
}