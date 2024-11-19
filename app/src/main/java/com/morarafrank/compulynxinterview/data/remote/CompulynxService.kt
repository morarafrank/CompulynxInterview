package com.morarafrank.compulynxinterview.data.remote

import com.morarafrank.compulynxinterview.data.remote.model.AccountBalanceBody
import com.morarafrank.compulynxinterview.data.remote.model.AccountBalanceResponse
import com.morarafrank.compulynxinterview.data.remote.model.Last100Response
import com.morarafrank.compulynxinterview.data.remote.model.LoginBody
import com.morarafrank.compulynxinterview.data.remote.model.LoginResponse
import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyBody
import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyResponse
import com.morarafrank.compulynxinterview.data.remote.model.Transaction
import com.morarafrank.compulynxinterview.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface CompulynxService {

    @POST(Constants.Endpoints.LOGIN)
    suspend fun login(
        @Body loginBody: LoginBody

    ): LoginResponse

    @POST(Constants.Endpoints.CHECK_ACCOUNT_BALANCE)
    suspend fun checkAccountBalance(
        @Body balanceBody: AccountBalanceBody
    ): AccountBalanceResponse


    @POST(Constants.Endpoints.SEND_MONEY)
    suspend fun sendMoney(
        @Body sendMoneyBody: SendMoneyBody
    ): SendMoneyResponse


    @GET(Constants.Endpoints.GET_THE_LAST_100_TRANSACTIONS)
    suspend fun getTheLast100Transactions(): Last100Response


}