package com.morarafrank.compulynxinterview.data.local.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.morarafrank.compulynxinterview.utils.Constants
import kotlinx.serialization.Serializable

typealias LocalTransactions = List<LocalTransaction>

@Entity(tableName = Constants.TRANSACTIONS_TABLE)
data class LocalTransaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val customerID: String,
    val customerAccount: String,
    val accountTo: String,
    val amount: Double,
    val status: String
)