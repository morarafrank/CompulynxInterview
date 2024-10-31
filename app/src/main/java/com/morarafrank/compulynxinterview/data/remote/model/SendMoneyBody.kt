package com.morarafrank.compulynxinterview.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SendMoneyBody(
    val accountTo: String,
    val amount: String
): Parcelable


@Parcelize
data class SendMoneyResponse(
    val message: String,
    val data: String,
    val status: Int

): Parcelable