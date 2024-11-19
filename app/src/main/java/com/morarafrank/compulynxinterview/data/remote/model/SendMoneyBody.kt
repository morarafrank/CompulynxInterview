package com.morarafrank.compulynxinterview.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class SendMoneyBody(
    val customerId: String,
    val accountFrom: String,
    val accountTo: String,
    val amount: Int
)


@Serializable
data class SendMoneyResponse(
    val response_status: Boolean,
    val response_message: String

)