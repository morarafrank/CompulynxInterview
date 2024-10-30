package com.morarafrank.compulynxinterview.data.remote.model

import android.os.Parcelable
import com.morarafrank.compulynxinterview.data.local.transaction.Transaction
import kotlinx.parcelize.Parcelize

typealias Last100Transactions = List<Last100Transaction>

@Parcelize
data class Last100TransactionsResponse(
    val message: String,
    val data: Last100Transactions,
    val status: Int

): Parcelable

@Parcelize
data class Last100Transaction(
    val id: String,
    val accountFrom: String,
    val accountTo: String,
    val amount: String,
    val date: String
): Parcelable