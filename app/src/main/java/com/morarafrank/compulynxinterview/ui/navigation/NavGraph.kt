package com.morarafrank.compulynxinterview.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.morarafrank.compulynxinterview.ui.screens.CustomerProfileScreen
import com.morarafrank.compulynxinterview.ui.screens.HomeScreen
import com.morarafrank.compulynxinterview.ui.screens.LastTransactionsScreen
import com.morarafrank.compulynxinterview.ui.screens.LoginScreen
import com.morarafrank.compulynxinterview.ui.screens.SendMoneyScreen
import com.morarafrank.compulynxinterview.ui.screens.StatementScreen

@Composable
fun CompulynxInterviewNavGraph(
    navController: NavHostController,
    isSignedIn: Boolean,
) {

    NavHost(
        navController = navController,
        startDestination = if (isSignedIn) Screens.Home.route else Screens.Login.route
    ){

        composable(Screens.Login.route){

            LoginScreen(
                navigateToHome = {
                    navController.navigate(Screens.Home.route)
                }
            )
        }

        composable(Screens.Home.route){
            HomeScreen(
                navigateToStatement = {
                    navController.navigate(Screens.Statement.route)
                },
                navigateToSendMoney = {
                    navController.navigate(Screens.SendMoney.route)
                },
                navigateToCustomerProfile = {
                    navController.navigate(Screens.CustomerProfile.route)
                },
                navigateToLastTransactions = {
                    navController.navigate(Screens.LastTransactions.route)
                },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screens.CustomerProfile.route){
            CustomerProfileScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(Screens.LastTransactions.route){
            LastTransactionsScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(Screens.SendMoney.route){
            SendMoneyScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(Screens.Statement.route){
            StatementScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }

}