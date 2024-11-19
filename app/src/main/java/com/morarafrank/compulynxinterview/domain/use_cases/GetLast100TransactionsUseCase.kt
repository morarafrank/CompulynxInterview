package com.morarafrank.compulynxinterview.domain.use_cases

import com.morarafrank.compulynxinterview.domain.repo.CompulynxRepository
import javax.inject.Inject

class GetLast100TransactionsUseCase @Inject constructor(
    private val repository: CompulynxRepository
) {

    operator fun invoke() = repository.getLast100Transactions()
}