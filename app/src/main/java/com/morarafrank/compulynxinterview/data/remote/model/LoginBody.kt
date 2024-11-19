package com.morarafrank.compulynxinterview.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginBody(
    val customerId: String,
    val pin: String
)



@Serializable
data class LoginResponse(
    val customerName: String,
    val customerId: String,
    val email: String,
    val account: String,
)


@Serializable
data class _Customer(
    val id: String,
    val name: String,
    val account: String,
    val email: String,
    val pin: String
)