package com.morarafrank.compulynxinterview.domain.use_cases

import com.morarafrank.compulynxinterview.domain.repo.CompulynxRepository

class CheckAccountBalanceUseCase (
    private val repository: CompulynxRepository
){

    suspend operator fun invoke(
        customerId: String
    ) = repository.checkAccountBalance(
        customerId
    )
}