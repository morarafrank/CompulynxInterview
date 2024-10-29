package com.morarafrank.compulynxinterview.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.morarafrank.compulynxinterview.data.local.dao.TransactionsDao
import com.morarafrank.compulynxinterview.data.local.model.Transaction

@Database(
    entities = [Transaction::class],
    version = 1,
    exportSchema = false
)
abstract class CompulynxDb : RoomDatabase() {

//    abstract fun customerDao(): CustomerDao

    abstract fun transactionDao(): TransactionsDao
}