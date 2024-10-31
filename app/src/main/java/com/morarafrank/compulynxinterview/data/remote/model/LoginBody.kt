package com.morarafrank.compulynxinterview.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginBody(
    val customerId: String,
    val pin: String
): Parcelable

@Parcelize
data class LoginResponse(
    val message: String,
    val data: _Customer,
    val status: Int
) : Parcelable

@Parcelize
data class _Customer(
    val id: String,
    val name: String,
    val account: String,
    val email: String,
    val pin: String
): Parcelable