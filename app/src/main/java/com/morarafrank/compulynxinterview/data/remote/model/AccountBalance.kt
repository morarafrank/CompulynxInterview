package com.morarafrank.compulynxinterview.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountBalanceBody(
    val accountNo: String,
    val customerId: String
)

@Serializable
data class AccountBalanceResponse(
    val balance: Double,
    val accountNo: String
)