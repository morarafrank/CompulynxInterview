package com.morarafrank.compulynxinterview.domain.repo

import com.morarafrank.compulynxinterview.data.remote.model.AccountBalanceResponse
import com.morarafrank.compulynxinterview.data.remote.model.Last100TransactionsResponse
import com.morarafrank.compulynxinterview.data.remote.model.LoginBody
import com.morarafrank.compulynxinterview.data.remote.model.LoginResponse
import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyBody
import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyResponse
import com.morarafrank.compulynxinterview.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CompulynxRepository {

    suspend fun login(loginBody: LoginBody): Resource<LoginResponse>

    suspend fun sendMoney(sendMoneyBody: SendMoneyBody): Resource<SendMoneyResponse>

    suspend fun checkAccountBalance(customerID: String): Resource<AccountBalanceResponse>

    fun getLast100Transactions(customerID: String): Flow<Resource<Last100TransactionsResponse>>
}