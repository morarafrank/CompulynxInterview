package com.morarafrank.compulynxinterview.data.local.customer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

typealias Customers = List<Customer>
@Dao
interface CustomerDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCustomer(customer: Customer)

    @Query("SELECT * FROM customer")
    fun getCustomers(): Flow<Customers>

    @Delete
    suspend fun deleteCustomer(customer: Customer)

}