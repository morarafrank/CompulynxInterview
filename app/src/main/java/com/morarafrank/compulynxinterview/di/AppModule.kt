package com.morarafrank.compulynxinterview.di

import android.content.Context
import androidx.room.Room
import com.morarafrank.compulynxinterview.data.local.CompulynxDb
import com.morarafrank.compulynxinterview.data.local.customer.CustomerDao
import com.morarafrank.compulynxinterview.data.local.transaction.TransactionsDao
import com.morarafrank.compulynxinterview.data.remote.CompulynxService
import com.morarafrank.compulynxinterview.data.repo.CompulynxRepoImpl
import com.morarafrank.compulynxinterview.domain.repo.CompulynxRepository
import com.morarafrank.compulynxinterview.domain.use_cases.CheckAccountBalanceUseCase
import com.morarafrank.compulynxinterview.domain.use_cases.GetLast100TransactionsUseCase
import com.morarafrank.compulynxinterview.domain.use_cases.LoginUseCase
import com.morarafrank.compulynxinterview.domain.use_cases.SendMoneyUseCase
import com.morarafrank.compulynxinterview.ui.viewmodel.CompulynxViewModel
import com.morarafrank.compulynxinterview.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Room Database
    @Provides
    @Singleton
    fun provideMerchantsDb(@ApplicationContext context: Context): CompulynxDb {
        return Room.databaseBuilder(
            context,
            CompulynxDb::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    // Transaction Dao
    @Provides
    @Singleton
    fun provideTransactionDao(db: CompulynxDb) = db.transactionsDao()


    // Customer Dao
    @Provides
    @Singleton
    fun providesCustomerDao(db: CompulynxDb) = db.customerDao()



    // Compulynx Repository
    @Provides
    @Singleton
    fun provideCompulynxRepository(
        compulynxService: CompulynxService,
        transactionsDao: TransactionsDao,
        customerDao: CustomerDao
    ): CompulynxRepository {
        return CompulynxRepoImpl(
            compulynxService,
            transactionsDao,
            customerDao
        )
    }

    // send money use case
    @Provides
    fun provideSendMoneyUseCase(
        repository: CompulynxRepository
    ): SendMoneyUseCase {
        return SendMoneyUseCase(repository)
    }

    // login use case
    @Provides
    fun provideLoginUseCase(
        repository: CompulynxRepository
    ): LoginUseCase {
        return LoginUseCase(repository)
    }

    // get last 100 transactions use case
    @Provides
    fun provideGetLast100TransactionsUseCase(
        repository: CompulynxRepository
    ): GetLast100TransactionsUseCase {
        return GetLast100TransactionsUseCase(repository)
    }

    // check account balance use case
    @Provides
    fun provideCheckAccountBalanceUseCase(
        repository: CompulynxRepository
    ): CheckAccountBalanceUseCase {
        return CheckAccountBalanceUseCase(repository)
    }

    // Compulynx Viewmodel
    @Provides
    @Singleton
    fun provideCompulynxViewModel(
        loginUseCase: LoginUseCase,
        getLast100TransactionsUseCase: GetLast100TransactionsUseCase,
        sendMoneyUseCase: SendMoneyUseCase,
        checkBalanceUseCase: CheckAccountBalanceUseCase,
        repository: CompulynxRepository
    ): CompulynxViewModel {
        return CompulynxViewModel(
            loginUseCase,
            getLast100TransactionsUseCase,
            sendMoneyUseCase,
            checkBalanceUseCase,
            repository
        )
    }

}
