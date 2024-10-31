package com.morarafrank.compulynxinterview.data.remote

import com.morarafrank.compulynxinterview.data.remote.model.AccountBalanceResponse
import com.morarafrank.compulynxinterview.data.remote.model.Last100TransactionsResponse
import com.morarafrank.compulynxinterview.data.remote.model.LoginBody
import com.morarafrank.compulynxinterview.data.remote.model.LoginResponse
import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyBody
import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyResponse
import com.morarafrank.compulynxinterview.utils.Constants
import com.morarafrank.compulynxinterview.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.POST


interface CompulynxService {

    @POST(Constants.Endpoints.LOGIN)
    suspend fun login(
        @Body loginBody: LoginBody

    ): Resource<LoginResponse>

    @POST(Constants.Endpoints.CHECK_ACCOUNT_BALANCE)
    suspend fun checkAccountBalance(
        @Body customerID: String
    ): Resource<AccountBalanceResponse>


    @POST(Constants.Endpoints.SEND_MONEY)
    suspend fun sendMoney(
        @Body sendMoneyBody: SendMoneyBody
    ): Resource<SendMoneyResponse>


    @POST(Constants.Endpoints.GET_THE_LAST_100_TRANSACTIONS)
    suspend fun getTheLast100Transactions(
        @Body customerID: String
    ): Last100TransactionsResponse


}