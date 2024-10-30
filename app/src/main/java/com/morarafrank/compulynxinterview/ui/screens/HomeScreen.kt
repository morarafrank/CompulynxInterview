package com.morarafrank.compulynxinterview.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morarafrank.compulynxinterview.ui.theme.fontFamily
import com.morarafrank.compulynxinterview.R

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
//    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
//    navigateToCustomerProfile: () -> Unit,
//    navigateToLastTransactions: () -> Unit,
//    navigateToSendMoney: () -> Unit,
//    navigateToStatement: () -> Unit
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


                Row(
                    modifier = modifier.fillMaxWidth()
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

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.weight(0.5f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.balance),
                            fontFamily = fontFamily,
                            modifier = modifier.padding(4.dp)
                        )
                    }

                    Button(
                        onClick = { /*TODO*/ },
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
                        onClick = { /*TODO*/ },
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
                        onClick = { /*TODO*/ },
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
                        onClick = { /*TODO*/ },
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
                        onClick = { /*TODO*/ },
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