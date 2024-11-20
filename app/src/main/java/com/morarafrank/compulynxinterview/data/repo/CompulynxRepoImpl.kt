package com.morarafrank.compulynxinterview.data.repo

import com.morarafrank.compulynxinterview.data.local.customer.Customer
import com.morarafrank.compulynxinterview.data.local.customer.CustomerDao
import com.morarafrank.compulynxinterview.data.local.customer.Customers
import com.morarafrank.compulynxinterview.data.local.transaction.LocalTransaction
import com.morarafrank.compulynxinterview.data.local.transaction.LocalTransactions
import com.morarafrank.compulynxinterview.data.local.transaction.TransactionsDao
import com.morarafrank.compulynxinterview.data.remote.CompulynxService
import com.morarafrank.compulynxinterview.data.remote.model.AccountBalanceBody
import com.morarafrank.compulynxinterview.data.remote.model.AccountBalanceResponse
import com.morarafrank.compulynxinterview.data.remote.model.Last100Transactions
import com.morarafrank.compulynxinterview.data.remote.model.LoginBody
import com.morarafrank.compulynxinterview.data.remote.model.LoginResponse
import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyBody
import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyResponse
import com.morarafrank.compulynxinterview.domain.repo.CompulynxRepository
import com.morarafrank.compulynxinterview.utils.CompulynxAndroidInterviewSharedPrefs
import com.morarafrank.compulynxinterview.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CompulynxRepoImpl @Inject constructor(
    private val compulynxService: CompulynxService,
    private val transactionsDao: TransactionsDao,
    private val customerDao: CustomerDao
): CompulynxRepository {
    override suspend fun login(loginBody: LoginBody): Resource<LoginResponse> {
        return try {
            val response = compulynxService.login(loginBody)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }


    override suspend fun getCustomer(): Flow<Customers> = customerDao.getCustomers()

    override suspend fun addCustomer(customer: Customer): Resource<Boolean> {

        return try {
            customerDao.addCustomer(customer)
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e)
            }
    }

    override suspend fun saveSendTransaction(transaction: LocalTransaction) = try {
        transactionsDao.addTransaction(transaction)
        Resource.Success(true)
    } catch (e: Exception) {
        Resource.Error(e)
    }

    override suspend fun sendMoney(sendMoneyBody: SendMoneyBody): Resource<SendMoneyResponse> {

        return try {
            val response =  compulynxService.sendMoney(sendMoneyBody)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }


    override suspend fun checkAccountBalance(): Resource<AccountBalanceResponse> {
        return try {
            val response =  compulynxService.checkAccountBalance(
                balanceBody = AccountBalanceBody(
                    accountNo = CompulynxAndroidInterviewSharedPrefs.getCustomerAccount(),
                    customerId = CompulynxAndroidInterviewSharedPrefs.getCustomerId()
                )
            )
            Resource.Success(response)
        }catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override fun getLast100Transactions(): Flow<Resource<Last100Transactions>> = flow {
        emit(Resource.Loading)

        try {
            val response = compulynxService.getTheLast100Transactions()

            val customerTransactions = response.content.filter { it.customerId == CompulynxAndroidInterviewSharedPrefs.getCustomerId() }

            if (customerTransactions.isNotEmpty()) {
                emit(Resource.Success(customerTransactions))
            } else {
                emit(Resource.Success(emptyList()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }



    override fun getRoomTransactions(): Flow<LocalTransactions> = transactionsDao.getAllTransactions()
    override suspend fun deleteAllCustomers(): Resource<Boolean> {
        try {
            customerDao.deleteAllCustomers()
            return Resource.Success(true)
        }catch (e: Exception){
            return Resource.Error(e)
        }
    }

    override suspend fun deleteAllTransactions(): Resource<Boolean> {
        try {
            transactionsDao.deleteAllTransactions()
            return Resource.Success(true)
        }catch (e: Exception){
            return Resource.Error(e)
        }
    }


}