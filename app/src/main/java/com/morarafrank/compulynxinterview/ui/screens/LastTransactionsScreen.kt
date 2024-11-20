package com.morarafrank.compulynxinterview.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.morarafrank.compulynxinterview.R
import com.morarafrank.compulynxinterview.ui.composables.NoDataUi
import com.morarafrank.compulynxinterview.ui.composables.TransactionsTableUi
import com.morarafrank.compulynxinterview.ui.composables.generateTransactionList
import com.morarafrank.compulynxinterview.ui.theme.fontFamily
import com.morarafrank.compulynxinterview.ui.viewmodel.CompulynxViewModel
import com.morarafrank.compulynxinterview.utils.Resource
import kotlinx.coroutines.delay

// LISTS ALL LAST 100 TRANSACTIONS FOR A SINGLE CUSTOMER

//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LastTransactionsScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CompulynxViewModel = hiltViewModel()
) {

    val transactions = viewModel.last100Transactions.collectAsState().value
    val errorMessage = viewModel.errorMessage.collectAsState()

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
                        Icon(painter = painterResource(R.drawable.ic_arrow_back), contentDescription = "Back IconButton")
                    }
                }
            )
        },
        content = {



            when(transactions){
                is Resource.Loading -> {
                    CircularProgressIndicator(
                        modifier = modifier.size(50.dp),
                    )
                }
                is Resource.Success -> {
                    if (transactions.data.isEmpty()){
                        NoDataUi(
                            error = "No transactions found"
                        )
                    }else{
                        TransactionsTableUi(
                            modifier = modifier.padding(it).padding(16.dp),
                            transactions = transactions.data
                        )
                    }
                }
                is Resource.Idle -> {
                    CircularProgressIndicator(
                        modifier = modifier.size(50.dp),
                    )
                }
                is Resource.Error -> {
                    NoDataUi(
                        error = errorMessage.toString()
                    )
                }
            }
        }
    )
}


