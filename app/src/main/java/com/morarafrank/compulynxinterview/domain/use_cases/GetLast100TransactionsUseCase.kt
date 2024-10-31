package com.morarafrank.compulynxinterview.domain.use_cases

import com.morarafrank.compulynxinterview.domain.repo.CompulynxRepository

class GetLast100TransactionsUseCase(
    private val repository: CompulynxRepository
) {

    suspend operator fun invoke(
        customerID: String
    ) = repository.getLast100Transactions(
        customerID
    )
}