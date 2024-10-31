package com.morarafrank.compulynxinterview.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.morarafrank.compulynxinterview.data.local.customer.Customer
import com.morarafrank.compulynxinterview.data.local.customer.Customers
import com.morarafrank.compulynxinterview.data.local.transaction.Transaction
import com.morarafrank.compulynxinterview.data.local.transaction.Transactions
import com.morarafrank.compulynxinterview.data.remote.model.AccountBalanceResponse
import com.morarafrank.compulynxinterview.data.remote.model.Last100Transactions
import com.morarafrank.compulynxinterview.data.remote.model.Last100TransactionsResponse
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
import com.morarafrank.compulynxinterview.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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

    private val _loginUiState = MutableStateFlow<Resource<LoginResponse>>(Resource.Loading)
    val loginUiState = _loginUiState.asStateFlow()

    private val _sendMoneyUiState = MutableStateFlow<Resource<SendMoneyResponse>>(Resource.Loading)
    val sendMoneyUiState = _sendMoneyUiState.asStateFlow()

    private val _saveSendUiState = MutableStateFlow<Resource<Boolean>>(Resource.Loading)
    val saveSendUiState = _saveSendUiState.asStateFlow()

    private val _checkBalanceUiState = MutableStateFlow<Resource<AccountBalanceResponse>>(Resource.Loading)
    val checkBalanceUiState = _checkBalanceUiState.asStateFlow()

    var balance: String = ""

    private val _last100TransactionsUiState = MutableStateFlow<Resource<Last100TransactionsResponse>>(Resource.Loading)
    val last100TransactionsUiState = _last100TransactionsUiState.asStateFlow()

    private val _transactions = MutableStateFlow<Last100Transactions>(emptyList())
    val transactions: StateFlow<Last100Transactions>
        get() = _transactions

    private val _roomTransactions = MutableStateFlow<Transactions>(emptyList())
    val roomTransactions: StateFlow<Transactions>
        get() = _roomTransactions


    private val _customers = MutableStateFlow<Customers>(emptyList())
    val customers: StateFlow<Customers>
        get() = _customers


    fun login(loginBody: LoginBody) = viewModelScope.launch {

        _uiState.value = UiState.Idle
        try {
            _loginUiState.value = Resource.Loading


            when(val response = loginUseCase(loginBody)){
                is Resource.Loading -> {
                    _loginUiState.value = Resource.Loading
                }
                is Resource.Success -> {
                    _loginUiState.value = Resource.Success(response.data)

                    // Add Customer to room database
                    val customer = response.data.data
                    CompulynxAndroidInterviewSharedPrefs.saveCustomerId(customer.id)
                    repository.addCustomer(
                        Customer(
                            id = customer.id.toInt(),
                            customerId = customer.id,
                            customerName = customer.name,
                            customerAccount = customer.account,
                            customerEmail = customer.email

                        )
                    )
                }

                is Resource.Error -> {
                    _loginUiState.value = Resource.Error(response.throwable)
                    Log.i("LOGIN ERROR:", response.throwable.toString())
                }
            }

        }catch (e: Exception){
            _loginUiState.value = Resource.Error(e)
        }
    }

    fun sendMoney(
        sendMoneyBody: SendMoneyBody,
        sendTransaction: Transaction
    ) = viewModelScope.launch {

        try {
            _sendMoneyUiState.value = Resource.Loading

            when(val saveSendTransaction = repository.saveSendTransaction(sendTransaction)){
                is Resource.Loading -> {
                    _saveSendUiState.value = Resource.Loading
                }
                is Resource.Success -> {
                    _saveSendUiState.value = Resource.Success(true)

                    when(val response = sendMoneyUseCase(sendMoneyBody)){
                        is Resource.Loading -> {
                            _sendMoneyUiState.value = Resource.Loading
                        }
                        is Resource.Success -> {
                            _sendMoneyUiState.value = Resource.Success(response.data)
                        }

                        is Resource.Error -> {
                            _sendMoneyUiState.value = Resource.Error(response.throwable)
                        }
                    }
                }
                is Resource.Error -> {
                    _saveSendUiState.value = Resource.Error(saveSendTransaction.throwable)
                }
            }
        }catch (e: Exception){
            _sendMoneyUiState.value = Resource.Error(e)
        }
    }

    fun checkBalance(customerID: String) = viewModelScope.launch {

        try {
            _checkBalanceUiState.value = Resource.Loading

            when(val response = checkBalanceUseCase(customerID)){
                is Resource.Loading -> {
                    _checkBalanceUiState.value = Resource.Loading
                }
                is Resource.Success -> {
                    _checkBalanceUiState.value = Resource.Success(response.data)
                    balance = response.data.data
                }

                is Resource.Error -> {
                    _checkBalanceUiState.value = Resource.Error(response.throwable)
                }
            }

        }catch (e: Exception){
            _checkBalanceUiState.value = Resource.Error(e)
        }
    }

    fun getLast100Transactions(customerID: String) = viewModelScope.launch {

        try {
            _last100TransactionsUiState.value = Resource.Loading
            getLast100TransactionsUseCase(customerID).collectLatest { transactions ->

                when(transactions){
                    is Resource.Loading -> {
                        _last100TransactionsUiState.value = Resource.Loading
                    }
                    is Resource.Success -> {
                        _last100TransactionsUiState.value = Resource.Success(transactions.data)
                        _transactions.value = transactions.data.data
                    }
                    is Resource.Error -> {
                        _last100TransactionsUiState.value = Resource.Error(transactions.throwable)
                    }
                }
            }

        }catch (e: Exception){
            _last100TransactionsUiState.value = Resource.Error(e)
        }
    }

//    fun getCustomer() = viewModelScope.launch {
//        repository.getCustomer().collectLatest { customer ->
//            _customer.value = customer
//        }
//    }

    fun addCustomer(customer: Customer) = viewModelScope.launch {
        repository.addCustomer(customer)
    }

    fun saveSendTransaction(transaction: Transaction) = viewModelScope.launch {
        repository.saveSendTransaction(transaction)
    }

    fun getRoomTransactions() = viewModelScope.launch {
        try {

            repository.getRoomTransactions().collectLatest { transactions ->
                if (transactions.isNotEmpty()){
                    _roomTransactions.value = transactions
                } else {
                    _roomTransactions.value = emptyList()
                }
            }
        }catch (e: Exception){
            _roomTransactions.value = emptyList()
        }
    }

    fun getCustomer() = viewModelScope.launch {
        repository.getCustomer().collectLatest { customers ->
            _customers.value = customers
        }
    }

    fun resetUiState(){
        _uiState.value = UiState.Idle
    }
}