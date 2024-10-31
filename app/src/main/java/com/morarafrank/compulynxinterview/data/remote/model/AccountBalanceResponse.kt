package com.morarafrank.compulynxinterview.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountBalanceResponse(
    val message: String,
    val data: String,
    val status: Int

): Parcelable