package com.morarafrank.compulynxinterview.domain.repo

import com.morarafrank.compulynxinterview.data.local.customer.Customer
import com.morarafrank.compulynxinterview.data.local.customer.Customers
import com.morarafrank.compulynxinterview.data.local.transaction.LocalTransaction
import com.morarafrank.compulynxinterview.data.local.transaction.LocalTransactions
import com.morarafrank.compulynxinterview.data.remote.model.AccountBalanceResponse
import com.morarafrank.compulynxinterview.data.remote.model.Last100Transactions
import com.morarafrank.compulynxinterview.data.remote.model.LoginBody
import com.morarafrank.compulynxinterview.data.remote.model.LoginResponse
import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyBody
import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyResponse
import com.morarafrank.compulynxinterview.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CompulynxRepository {

    suspend fun login(loginBody: LoginBody): Resource<LoginResponse>

    suspend fun getCustomer(): Flow<Customers>

    suspend fun addCustomer(customer: Customer): Resource<Boolean>

    suspend fun saveSendTransaction(transaction: LocalTransaction): Resource<Boolean>

    suspend fun sendMoney(sendMoneyBody: SendMoneyBody): Resource<SendMoneyResponse>

    suspend fun checkAccountBalance(): Resource<AccountBalanceResponse>

    fun getLast100Transactions(): Flow<Resource<Last100Transactions>>

    fun getRoomTransactions(): Flow<LocalTransactions>
}