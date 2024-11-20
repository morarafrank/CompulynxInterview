package com.morarafrank.compulynxinterview.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import com.morarafrank.compulynxinterview.domain.repo.CompulynxRepository
import com.morarafrank.compulynxinterview.domain.use_cases.CheckAccountBalanceUseCase
import com.morarafrank.compulynxinterview.domain.use_cases.GetLast100TransactionsUseCase
import com.morarafrank.compulynxinterview.domain.use_cases.LoginUseCase
import com.morarafrank.compulynxinterview.domain.use_cases.SendMoneyUseCase
import com.morarafrank.compulynxinterview.utils.CompulynxAndroidInterviewSharedPrefs
import com.morarafrank.compulynxinterview.utils.Constants.NO_NUM
import com.morarafrank.compulynxinterview.utils.Constants.NO_VALUE
import com.morarafrank.compulynxinterview.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CompulynxViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getLast100TransactionsUseCase: GetLast100TransactionsUseCase,
    private val sendMoneyUseCase: SendMoneyUseCase,
    private val checkBalanceUseCase: CheckAccountBalanceUseCase,
    private val repository: CompulynxRepository
): ViewModel(){

    private val _loginUiState = MutableStateFlow<Resource<LoginResponse>>(Resource.Idle)
    val loginUiState = _loginUiState.asStateFlow()

    private val _sendMoneyUiState = MutableStateFlow<Resource<SendMoneyResponse>>(Resource.Idle)
    val sendMoneyUiState: StateFlow<Resource<SendMoneyResponse>>
        get() = _sendMoneyUiState


    private val _checkBalanceUiState = MutableStateFlow<Resource<AccountBalanceResponse>>(Resource.Idle)
    val checkBalanceUiState = _checkBalanceUiState.asStateFlow()

    private val _last100Transactions = MutableStateFlow<Resource<Last100Transactions>>(Resource.Idle)
    val last100Transactions = _last100Transactions
        .asStateFlow()
//        .onStart {
//            getLast100Transactions()
//        }
//        .stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(4000),
//            Resource.Loading
//        )

    private val _customer = MutableStateFlow<Customer?>(null)
    val customer: StateFlow<Customer?> get() = _customer.asStateFlow()


    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage


    init {
        getLast100Transactions()
//        getRoomTransactions()
    }

    private val _roomTransactions = MutableStateFlow<LocalTransactions>(emptyList())
    val roomTransactions = _roomTransactions
        .onStart {
            getRoomTransactions()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(4000),
            emptyList()
        )

    private val _balance = MutableStateFlow<String?>(null)
    val balance: StateFlow<String?> get() = _balance


    fun login(loginBody: LoginBody) = viewModelScope.launch {

        try {
            _loginUiState.value = Resource.Loading


            when(val response = loginUseCase(loginBody)){
                is Resource.Idle -> {
                    _loginUiState.value = Resource.Idle
                }
                is Resource.Loading -> {
                    _loginUiState.value = Resource.Loading
                }
                is Resource.Success -> {
                    _loginUiState.value = Resource.Success(response.data)

                    // Add Customer to room database
                    val customer = response.data
                    CompulynxAndroidInterviewSharedPrefs.saveCustomerId(customer.customerId)
                    CompulynxAndroidInterviewSharedPrefs.saveCustomerAccount(customer.account)
                    Log.i("LOGIN CUSTOMER", "CUSTOMER: $customer")
                    repository.addCustomer(
                        Customer(
                            customerId = customer.customerId,
                            customerName = customer.customerName,
                            customerAccount = customer.account,
                            customerEmail = customer.email

                        )
                    )
                }

                is Resource.Error -> {
                    _loginUiState.value = Resource.Error(response.throwable)
                    Log.i("LOGIN ERROR", "ERROR: ${response.throwable}")
                    _errorMessage.value = response.throwable.message.toString()
                }
            }

        }catch (e: Exception){
            _loginUiState.value = Resource.Error(e)
        }
    }

    fun sendMoney(
        sendMoneyBody: SendMoneyBody,
        sendTransaction: LocalTransaction
    ) = viewModelScope.launch {
        try {
            _sendMoneyUiState.value = Resource.Loading
            Log.i("SEND MONEY", "Starting sendMoneyUseCase")

            when (val response = sendMoneyUseCase(sendMoneyBody)) {
                is Resource.Idle -> {
                    _sendMoneyUiState.value = Resource.Idle
                }
                is Resource.Loading -> {
                    _sendMoneyUiState.value = Resource.Loading
                    Log.i("SEND MONEY", "LOADING state reached")
                }
                is Resource.Success -> {
                    _sendMoneyUiState.value = Resource.Success(response.data)
                    Log.i("SEND MONEY", "SUCCESS: ${response.data}")

                    Log.i("SEND MONEY", "Calling repository.saveSendTransaction")
                    when (val saveSendTransaction = repository.saveSendTransaction(sendTransaction)) {
                        is Resource.Idle -> {}
                        is Resource.Loading -> {
                            Log.i("SAVE TRANSACTION", "LOADING state reached")
                        }
                        is Resource.Success -> {
                            Log.i("SAVE TRANSACTION", "SUCCESS: Transaction saved")
                        }
                        is Resource.Error -> {
                            Log.e("SAVE TRANSACTION", "ERROR: ${saveSendTransaction.throwable}")
                        }
                    }
                }
                is Resource.Error -> {
                    _sendMoneyUiState.value = Resource.Error(response.throwable)
                    Log.e("SEND MONEY", "ERROR: ${response.throwable}")
                    _errorMessage.value = response.throwable.message.toString()
                }
            }
        } catch (e: Exception) {
            _sendMoneyUiState.value = Resource.Error(e)
            Log.e("SEND MONEY", "Exception caught: $e")
        }
    }




    fun checkBalance() = viewModelScope.launch {
        try {
            _checkBalanceUiState.value = Resource.Loading

            when (val response = checkBalanceUseCase()) {
                is Resource.Idle -> {
                    _checkBalanceUiState.value = Resource.Idle
                }
                is Resource.Loading -> {
                    _checkBalanceUiState.value = Resource.Loading
                }
                is Resource.Success -> {
                    _checkBalanceUiState.value = Resource.Success(response.data)
                    _balance.value = response.data.balance.toString()
                    Log.i("BALANCE", "BALANCE: $balance")
                }
                is Resource.Error -> {
                    _checkBalanceUiState.value = Resource.Error(response.throwable)
                    Log.i("BALANCE", "BALANCE ERROR: ${response.throwable.message}")
                    _errorMessage.value = response.throwable.message.toString()
                }
            }
        } catch (e: Exception) {
            _checkBalanceUiState.value = Resource.Error(e)
        }
    }




    fun getLast100Transactions() = viewModelScope.launch {
        _last100Transactions.value = Resource.Loading

        try {
            getLast100TransactionsUseCase().collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _last100Transactions.value = Resource.Loading
                    }
                    is Resource.Success -> {
                        val transactions = resource.data
                        if (transactions.isNotEmpty()) {
                            _last100Transactions.value = Resource.Success(transactions)
                            Log.i("100TRANSACTIONS", "Transactions: $transactions")
                        } else {
                            _last100Transactions.value = Resource.Success(emptyList())
                        }
                    }
                    is Resource.Error -> {
                        _last100Transactions.value = Resource.Error(resource.throwable)
                        Log.i("100TRANSACTIONS", "Error: ${resource.throwable}")
                    }
                    else -> {
                        _last100Transactions.value = Resource.Idle
                    }
                }
            }
        } catch (e: Exception) {
            _last100Transactions.value = Resource.Error(e)
        }
    }


    fun getRoomTransactions() = viewModelScope.launch {
        try {
            Log.i("ROOM TRANSACTIONS", "Getting transactions")
            repository.getRoomTransactions().collectLatest { transactions ->
                _roomTransactions.value = transactions
                Log.i("ROOM TRANSACTIONS", "Transactions: $transactions")
            }
        } catch (e: Exception) {
            Log.e("ROOM TRANSACTIONS", "Error fetching transactions", e)
            _roomTransactions.value = emptyList()
        }
    }


    fun getCustomer() = viewModelScope.launch {
        try {
            repository.getCustomer().collectLatest { customers ->
                val firstCustomer = customers.firstOrNull()
                _customer.value = firstCustomer // Update reactive state
                Log.i("CUSTOMER", "Customer: $firstCustomer")
            }
        } catch (e: Exception) {
            Log.e("CUSTOMER", "Error fetching customer", e)
            _customer.value = null
        }
    }


    fun resetUiState(){
        _checkBalanceUiState.value = Resource.Idle
        _loginUiState.value = Resource.Idle
        _sendMoneyUiState.value = Resource.Idle
        _errorMessage.value = ""
    }

    fun deleteCustomerFromRoom() = viewModelScope.launch {
        repository.deleteAllCustomers()
    }

    fun deleteTransactionsFromRoom() = viewModelScope.launch{
        repository.deleteAllTransactions()
    }

    fun logOut(){
        CompulynxAndroidInterviewSharedPrefs.setIsLoggedIn(false)
        CompulynxAndroidInterviewSharedPrefs.clearInventorySharedPrefs()
        resetUiState()

        deleteCustomerFromRoom()
        deleteTransactionsFromRoom()
        _customer.value = null

    }
}