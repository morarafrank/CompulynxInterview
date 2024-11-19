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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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


    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _loginUiState = MutableStateFlow<Resource<LoginResponse>>(Resource.Idle)
    val loginUiState = _loginUiState.asStateFlow()

    private val _sendMoneyUiState = MutableStateFlow<Resource<SendMoneyResponse>>(Resource.Idle)
    val sendMoneyUiState: StateFlow<Resource<SendMoneyResponse>>
        get() = _sendMoneyUiState

    private val _saveSendUiState = MutableStateFlow<Resource<Boolean>>(Resource.Idle)
    val saveSendUiState = _saveSendUiState.asStateFlow()

    private val _checkBalanceUiState = MutableStateFlow<Resource<AccountBalanceResponse>>(Resource.Idle)
    val checkBalanceUiState = _checkBalanceUiState.asStateFlow()

    var balance: String = ""
    var errorMessage = ""

    private val _last100Transactions = MutableStateFlow<Resource<Last100Transactions>>(Resource.Loading)
    val last100Transactions = _last100Transactions.asStateFlow()

//    private val _transactions = MutableStateFlow<Last100Transactions>(emptyList())
//    val transactions = _transactions.asStateFlow()

    private val _transactions = MutableStateFlow<Last100Transactions>(emptyList())
    val transactions = _transactions.asStateFlow()

    init {
        getLast100Transactions()
    }

    private val _roomTransactions = MutableStateFlow<LocalTransactions>(emptyList())
    val roomTransactions: StateFlow<LocalTransactions>
        get() = _roomTransactions

    var customer: Customer? = Customer(
        NO_NUM, NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE
    )
        private set

    private val _customers = MutableStateFlow<Customers>(emptyList())
    val customers: StateFlow<Customers>
        get() = _customers


    fun login(loginBody: LoginBody) = viewModelScope.launch {

        try {
            _loginUiState.value = Resource.Idle


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
                    Timber.tag("LOGIN CUSTOMER").i("Customer: $customer")
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
                    Timber.tag("LOGIN ERROR:").i(response.throwable.toString())
                    errorMessage = response.throwable.message.toString()
                }
            }

        }catch (e: Exception){
            _loginUiState.value = Resource.Error(e)
        }
    }

//    fun sendMoney(
//        sendMoneyBody: SendMoneyBody,
//        sendTransaction: LocalTransaction
//    ) = viewModelScope.launch {
//
//        try {
//            _sendMoneyUiState.value = Resource.Idle
//
//            when(val response = sendMoneyUseCase(sendMoneyBody)){
//                is Resource.Idle -> {
//                    _sendMoneyUiState.value = Resource.Idle
//                    Log.i("SEND MONEY", "IDLE")
//                }
//                is Resource.Loading -> {
//                    _sendMoneyUiState.value = Resource.Loading
//                    Log.i("SEND MONEY", "LOADING")
//                }
//                is Resource.Success -> {
//                    _sendMoneyUiState.value = Resource.Success(response.data)
//
//                    Log.i("SEND MONEY", "SUCCESS: ${response.data}")
//
//                    when(val saveSendTransaction = repository.saveSendTransaction(sendTransaction)){
//                        is Resource.Idle -> {
//                            _saveSendUiState.value = Resource.Idle
//                        }
//                        is Resource.Loading -> {
//                            _saveSendUiState.value = Resource.Loading
//                        }
//                        is Resource.Success -> {
//                            _saveSendUiState.value = Resource.Success(true)
//                            Log.i("SAVE TRANSACTION: ", "SUCCESS")
//
//                        }
//                        is Resource.Error -> {
//                            _saveSendUiState.value = Resource.Error(saveSendTransaction.throwable)
//                            Log.i("SAVE TRANSACTION", "ERROR")
//                        }
//                    }
//
//                }
//
//                is Resource.Error -> {
//                    _sendMoneyUiState.value = Resource.Error(response.throwable)
//                    Log.i("SEND MONEY", "ERROR: ${response.throwable}")
//                    errorMessage = response.throwable.message.toString()
//                }
//            }
//
//
//
//
//
//        }catch (e: Exception){
//            _sendMoneyUiState.value = Resource.Error(e)
//        }
//    }

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
                    Log.i("SEND MONEY", "IDLE state reached")
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
                        is Resource.Idle -> {
                            _saveSendUiState.value = Resource.Idle
                            Log.i("SAVE TRANSACTION", "IDLE state reached")
                        }
                        is Resource.Loading -> {
                            _saveSendUiState.value = Resource.Loading
                            Log.i("SAVE TRANSACTION", "LOADING state reached")
                        }
                        is Resource.Success -> {
                            _saveSendUiState.value = Resource.Success(true)
                            Log.i("SAVE TRANSACTION", "SUCCESS: Transaction saved")
                        }
                        is Resource.Error -> {
                            _saveSendUiState.value = Resource.Error(saveSendTransaction.throwable)
                            Log.e("SAVE TRANSACTION", "ERROR: ${saveSendTransaction.throwable}")
                        }
                    }
                }
                is Resource.Error -> {
                    _sendMoneyUiState.value = Resource.Error(response.throwable)
                    Log.e("SEND MONEY", "ERROR: ${response.throwable}")
                }
            }
        } catch (e: Exception) {
            _sendMoneyUiState.value = Resource.Error(e)
            Log.e("SEND MONEY", "Exception caught: $e")
        }
    }


    fun checkBalance() = viewModelScope.launch {

        try {
            _checkBalanceUiState.value = Resource.Idle

            when(val response = checkBalanceUseCase()){
                is Resource.Idle -> {
                    _checkBalanceUiState.value = Resource.Idle
                }
                is Resource.Loading -> {
                    _checkBalanceUiState.value = Resource.Loading
                }
                is Resource.Success -> {
                    _checkBalanceUiState.value = Resource.Success(response.data)
                    balance = response.data.balance.toString()

                    Timber.tag("BALANCE").i("${response.data}")
                    Log.i("BALANCE", "${response.data}")
                    Log.i("BALANCE", "Response: $response")
                }

                is Resource.Error -> {
                    _checkBalanceUiState.value = Resource.Error(response.throwable)
                    Timber.tag("BALANCE").i("ERROR: ${response.throwable.message}")
                    Log.i("BALANCE", "${response}")
                    errorMessage = response.throwable.message.toString()
                }
            }

        }catch (e: Exception){
            _checkBalanceUiState.value = Resource.Error(e)
        }
    }

    fun getLast100Transactions() = viewModelScope.launch {
        try {
            _last100Transactions.value = Resource.Idle
            getLast100TransactionsUseCase().collectLatest { last100Transactions ->

                when (last100Transactions) {
                    is Resource.Idle -> {}
                    is Resource.Loading -> {
                        _last100Transactions.value = Resource.Loading
                    }
                    is Resource.Success -> {
                        val filteredTransactions = last100Transactions.data

                        if (filteredTransactions.isNotEmpty()) {
                            _transactions.value = filteredTransactions
                            Log.i("TRANSACTIONS", "Filtered: $filteredTransactions")
                        } else {
                            _transactions.value = emptyList()
                            Log.i("TRANSACTIONS", "No transactions found for customer ID: ${CompulynxAndroidInterviewSharedPrefs.getCustomerId()}")
                        }

                        _last100Transactions.value = Resource.Success(filteredTransactions)
                    }
                    is Resource.Error -> {
                        _last100Transactions.value = Resource.Error(last100Transactions.throwable)
                        Log.i("TRANSACTIONS", "ERROR ${last100Transactions.throwable}")
                        errorMessage = last100Transactions.throwable.message.toString()
                    }
                }
            }
        } catch (e: Exception) {
            _last100Transactions.value = Resource.Error(e)
        }
    }



//    fun getCustomer() = viewModelScope.launch {
//        repository.getCustomer().collectLatest { customer ->
//            _customer.value = customer
//        }
//    }


    fun saveSendTransaction(transaction: LocalTransaction) = viewModelScope.launch {
        repository.saveSendTransaction(transaction)
    }

//    fun getRoomTransactions() = viewModelScope.launch {
//        try {
//
//            Log.i("ROOM TRANSACTIONS", "Getting transactions")
//            repository.getRoomTransactions().collectLatest { transactions ->
//                if (transactions.isNotEmpty()){
//                    _roomTransactions.value = transactions
//                    Log.i("ROOM TRANSACTIONS", "Transactions: $transactions")
//                } else {
//                    _roomTransactions.value = emptyList()
//                    Log.i("ROOM TRANSACTIONS", "No transactions found")
//                }
//            }
//        }catch (e: Exception){
//            _roomTransactions.value = emptyList()
//        }
//    }

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
        repository.getCustomer().collectLatest { customers ->
            if (customers.isNotEmpty()){
                _customers.value = customers
                customer = customers.firstOrNull()
                Log.i("CUSTOMER", "Customer: $customer")
            }else{
                _customers.value = emptyList()
                customer = null
                Log.i("CUSTOMER", "No customer found")
            }
        }
    }

    fun resetUiState(){
        _checkBalanceUiState.value = Resource.Idle
        _uiState.value = UiState.Idle
        _loginUiState.value = Resource.Idle
        errorMessage = ""
    }

    private val _navigateToLogin = MutableStateFlow(false)
    val navigateToLogin: StateFlow<Boolean> get() = _navigateToLogin

    fun logOut(){
        CompulynxAndroidInterviewSharedPrefs.setIsLoggedIn(false)
        CompulynxAndroidInterviewSharedPrefs.clearInventorySharedPrefs()
        resetUiState()

        _navigateToLogin.value = true
    }
}