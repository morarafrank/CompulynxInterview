package com.morarafrank.compulynxinterview.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.morarafrank.compulynxinterview.data.local.customer.Customer
import com.morarafrank.compulynxinterview.data.local.customer.CustomerDao
import com.morarafrank.compulynxinterview.data.local.transaction.Transaction
import com.morarafrank.compulynxinterview.data.local.transaction.TransactionsDao

@Database(
    entities = [Transaction::class, Customer::class],
    version = 1,
    exportSchema = false
)
abstract class CompulynxDb : RoomDatabase() {

    abstract fun customerDao(): CustomerDao

    abstract fun transactionsDao(): TransactionsDao
}