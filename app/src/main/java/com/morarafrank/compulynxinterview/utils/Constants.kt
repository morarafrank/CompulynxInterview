package com.morarafrank.compulynxinterview.utils

object Constants {
    const val BASE_URL = ""
    const val DATABASE_NAME = "compulynx_db"

    object Preferences {
        const val SHARED_PREF_NAME = "compulynx_shared_pref"
    }

    const val TRANSACTIONS_TABLE = "transactions"
    const val CUSTOMER_TABLE = "customers"

    object Endpoints {

        const val LOGIN = "/api/v1/customers/login"
        const val CHECK_ACCOUNT_BALANCE = "/api/v1/accounts/balance"
        const val GET_THE_LAST_100_TRANSACTIONS = "/api/v1/transactions/last-100-transactions"
        const val SEND_MONEY = "/api/v1/transactions/send-money"
    }

}