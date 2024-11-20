package com.morarafrank.compulynxinterview.data.local.transaction

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.morarafrank.compulynxinterview.utils.Constants

@Dao
interface TransactionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTransaction(transaction: LocalTransaction)

    @Delete
    suspend fun deleteTransaction(transaction: LocalTransaction)

    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): Flow<LocalTransactions>

    @Query("DELETE FROM transactions")
    suspend fun deleteAllTransactions()
}