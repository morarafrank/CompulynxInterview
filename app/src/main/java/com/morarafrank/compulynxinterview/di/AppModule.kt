package com.morarafrank.compulynxinterview.di

import android.content.Context
import androidx.room.Room
import com.morarafrank.compulynxinterview.data.local.CompulynxDb
import com.morarafrank.compulynxinterview.data.local.customer.CustomerDao
import com.morarafrank.compulynxinterview.data.local.transaction.TransactionsDao
import com.morarafrank.compulynxinterview.data.remote.CompulynxService
import com.morarafrank.compulynxinterview.data.repo.CompulynxRepoImpl
import com.morarafrank.compulynxinterview.domain.repo.CompulynxRepository
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
}
