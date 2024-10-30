package com.morarafrank.compulynxinterview.data.repo

import com.morarafrank.compulynxinterview.data.remote.CompulynxService
import com.morarafrank.compulynxinterview.data.remote.model.AccountBalanceResponse
import com.morarafrank.compulynxinterview.data.remote.model.Last100TransactionsResponse
import com.morarafrank.compulynxinterview.data.remote.model.LoginBody
import com.morarafrank.compulynxinterview.data.remote.model.LoginResponse
import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyBody
import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyResponse
import com.morarafrank.compulynxinterview.domain.repo.CompulynxRepository
import com.morarafrank.compulynxinterview.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CompulynxRepoImpl @Inject constructor(
    private val compulynxService: CompulynxService
): CompulynxRepository {
    override suspend fun login(loginBody: LoginBody): Resource<LoginResponse> {
        return try {
            return compulynxService.login(loginBody)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun sendMoney(sendMoneyBody: SendMoneyBody): Resource<SendMoneyResponse> {

        return try {
            return compulynxService.sendMoney(sendMoneyBody)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }


    override suspend fun checkAccountBalance(customerID: String): Resource<AccountBalanceResponse> {
        return try {
            return compulynxService.checkAccountBalance(customerID)
        }catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override fun getLast100Transactions(customerID: String): Flow<Resource<Last100TransactionsResponse>> = flow {

        emit(Resource.Loading)
        val response = compulynxService.getTheLast100Transactions(customerID)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it))
    }


}