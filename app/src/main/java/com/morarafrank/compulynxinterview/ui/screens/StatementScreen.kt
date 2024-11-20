package com.morarafrank.compulynxinterview.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.morarafrank.compulynxinterview.R
import com.morarafrank.compulynxinterview.ui.composables.NoDataUi
import com.morarafrank.compulynxinterview.ui.composables.RoomTransactionsTableUi
import com.morarafrank.compulynxinterview.ui.composables.SingleTransactionRow
import com.morarafrank.compulynxinterview.ui.composables.generateTransactionList
import com.morarafrank.compulynxinterview.ui.theme.fontFamily
import com.morarafrank.compulynxinterview.ui.viewmodel.CompulynxViewModel
import com.morarafrank.compulynxinterview.utils.Resource

// LISTS ALL LAST 100 TRANSACTIONS FROM ROOM DB
//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatementScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CompulynxViewModel = hiltViewModel()
) {

    val roomTransactions by viewModel.roomTransactions.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.statement),
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
            if (roomTransactions.isNotEmpty()){
                RoomTransactionsTableUi(
                    modifier = modifier.padding(it).padding(16.dp),
                    transactions = roomTransactions
                )
            }else{
                NoDataUi(
                    error = "No Transactions Found",
                )
            }
        }
    )
}

