package com.morarafrank.compulynxinterview.data.remote.model

import kotlinx.serialization.Serializable

typealias Last100Transactions = List<Transaction>



@Serializable
data class Transaction(
    val id: Int,
    val transactionId: String,
    val customerId: String,
    val accountNo: String,
    val amount: Double,
    val balance: Double,
    val transactionType: String,
    val debitOrCredit: String
)

@Serializable
data class Last100Response(
    val total: Int,
    val content: List<Transaction>,
    val pageable: Pageable
)
@Serializable
data class Pageable(
    val sort: Sort,
    val page: Int,
    val size: Int
)
@Serializable
data class Sort(
    val orders: List<Order>
)
@Serializable
data class Order(
    val direction: String,
    val property: String,
    val ignoreCase: Boolean,
    val nullHandling: String
)

