package com.morarafrank.compulynxinterview.ui.navigation

sealed class Screens(val route: String) {
    object Login :Screens("login")
    object Home :Screens("home")
    object SendMoney :Screens("send_money")
    object Statement :Screens("statement")
    object CustomerProfile :Screens("customer_profile")
    object LastTransactions:Screens("last_transactions")
}