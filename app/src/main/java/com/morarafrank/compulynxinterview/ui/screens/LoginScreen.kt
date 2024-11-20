package com.morarafrank.compulynxinterview.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.morarafrank.compulynxinterview.R
import com.morarafrank.compulynxinterview.data.remote.model.LoginBody
import com.morarafrank.compulynxinterview.ui.theme.fontFamily
import com.morarafrank.compulynxinterview.ui.viewmodel.CompulynxViewModel
import com.morarafrank.compulynxinterview.utils.CompulynxAndroidInterviewSharedPrefs
import com.morarafrank.compulynxinterview.utils.Resource
import com.morarafrank.compulynxinterview.utils.UiUtils
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
//    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    viewModel: CompulynxViewModel = hiltViewModel()
) {

    val loginState by viewModel.loginUiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.login),
                        fontSize = 18.sp,
                        fontFamily = fontFamily
                    )
                }
            )
        },
        content = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var customerId by remember { mutableStateOf("") }
//                var customerId = CompulynxAndroidInterviewSharedPrefs.getCustomerId()
                var pin by remember { mutableStateOf("") }

                TextField(
                    value = customerId,
                    onValueChange = {
                        customerId = it
                    },
                    modifier = modifier.fillMaxWidth(),
                    placeholder = { Text(text = "Customer ID", fontFamily = fontFamily) }
                )

                TextField(
                    value = pin,
                    onValueChange = {
                        pin = it
                    },
                    modifier = modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "PIN",
                            fontFamily = fontFamily
                        )
                    }
                )

                Button(
                    onClick = {
//                        navigateToHome()
                        viewModel.login(
                            loginBody = LoginBody(
                                customerId = customerId,
                                pin = pin
                            )
                        )
                    },
                    modifier = modifier.fillMaxWidth()
                ) {

                    when(loginState){
                        is Resource.Idle -> {
                            Text(
                                text = stringResource(id = R.string.login),
                                fontFamily = fontFamily,
                                modifier = modifier.padding(8.dp)
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
                                stringResource(id = R.string.login_successful),
                                fontFamily = fontFamily,
                                modifier = modifier.padding(4.dp)
                            )
                            LaunchedEffect(Unit) {
                                delay(500)
                                CompulynxAndroidInterviewSharedPrefs.setIsLoggedIn(true)
                                navigateToHome()
                                viewModel.resetUiState()
                            }
                        }
                        is Resource.Error -> {
                            Text(
                                text = stringResource(id = R.string.login_failed),
                                fontFamily = fontFamily,
                                modifier = modifier.padding(4.dp)
                            )
                            UiUtils.showToast("Login Failed", context)
                            LaunchedEffect(Unit) {
                                delay(3000)
                            }
                            viewModel.resetUiState()
                        }
                    }
                }
            }
        }
    )
}