package com.morarafrank.compulynxinterview.di

import android.content.Context
import androidx.room.Room
import com.morarafrank.compulynxinterview.data.local.CompulynxDb
import com.morarafrank.compulynxinterview.data.local.dao.TransactionsDao
import com.morarafrank.compulynxinterview.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RoomDbModule {


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
    fun provideTransactionDao(db: CompulynxDb) = db.transactionDao()


}