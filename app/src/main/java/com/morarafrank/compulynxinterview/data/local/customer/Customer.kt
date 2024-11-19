package com.morarafrank.compulynxinterview.data.local.customer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class Customer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val customerId: String,
    val customerName: String,
    val customerAccount: String,
    val customerEmail: String
)