package com.morarafrank.compulynxinterview.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.morarafrank.compulynxinterview.ui.theme.fontFamily
import com.morarafrank.compulynxinterview.R
import com.morarafrank.compulynxinterview.ui.viewmodel.CompulynxViewModel
import com.morarafrank.compulynxinterview.utils.CompulynxAndroidInterviewSharedPrefs

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

    val showBalanceUiState by viewModel.checkBalanceUiState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

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
                            fontFamily = fontFamily
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
                var customerId by remember{ mutableStateOf("") }

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
                        fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
                        modifier = modifier.padding(4.dp),
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Morara Frank",
//                        fontFamily = FontFamily(Font(R.font.dm_sans_medium)),
                        fontFamily = fontFamily,
                        modifier = modifier.padding(4.dp),
                        fontSize = 20.sp
                    )
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){

                    AnimatedVisibility(visible = showBalance) {

                        Text(
                            text = viewModel.balance ?: "0",
                            fontFamily = fontFamily,
                            modifier = modifier.padding(4.dp)
                        )
                    }

                    AnimatedVisibility(visible = !showBalance) {
                        Button(
                            onClick = {
                                viewModel.checkBalance(customerID = customerId)
//                                showBalance = !showBalance
                            },
                            modifier = Modifier.weight(0.5f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.balance),
                                fontFamily = fontFamily,
                                modifier = modifier.padding(4.dp)
                            )
                        }
                    }

                    Button(
                        onClick = {
                            navigateToSendMoney()
                        },
                        modifier = Modifier.weight(0.5f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.send_money),
                            fontFamily = fontFamily,
                            modifier = modifier.padding(4.dp)
                        )
                    }
                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Button(
                        onClick = {
                            navigateToStatement()
                        },
                        modifier = Modifier.weight(0.5f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.view_statement),
                            fontFamily = fontFamily,
                            modifier = modifier.padding(4.dp)
                        )
                    }

                    Button(
                        onClick = {
                            navigateToLastTransactions()
                        },
                        modifier = Modifier.weight(0.5f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.last_transactions),
                            fontFamily = fontFamily,
                            fontSize = 13.sp,
                            modifier = modifier.padding(4.dp)
                        )
                    }

                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Button(
                        onClick = {
                            navigateToCustomerProfile()
                        },
                        modifier = Modifier.weight(0.5f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.profile),
                            fontFamily = fontFamily,
                            modifier = modifier.padding(4.dp)
                        )
                    }

                    Button(
                        onClick = {
//                            logout()
                            CompulynxAndroidInterviewSharedPrefs.setIsLoggedIn(false)
                            navigateBack()
                        },
                        modifier = Modifier.weight(0.5f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.logout),
                            fontFamily = fontFamily,
                            modifier = modifier.padding(4.dp)
                        )
                    }
                }
            }
        }
    )
}