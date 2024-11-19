package com.morarafrank.compulynxinterview.domain.use_cases

import com.morarafrank.compulynxinterview.domain.repo.CompulynxRepository
import javax.inject.Inject

class CheckAccountBalanceUseCase @Inject constructor (
    private val repository: CompulynxRepository
){

    suspend operator fun invoke() = repository.checkAccountBalance()
}