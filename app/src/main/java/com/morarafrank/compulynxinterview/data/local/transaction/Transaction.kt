package com.morarafrank.compulynxinterview.data.local.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.morarafrank.compulynxinterview.utils.Constants

typealias Transactions = List<Transaction>

@Entity(tableName = Constants.TRANSACTIONS_TABLE)
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int
)