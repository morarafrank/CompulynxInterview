package com.morarafrank.compulynxinterview.domain.use_cases

import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyBody
import com.morarafrank.compulynxinterview.domain.repo.CompulynxRepository

class SendMoneyUseCase (
    private val repository: CompulynxRepository
){
    suspend operator fun invoke(
        sendMoneyBody: SendMoneyBody
    ) = repository.sendMoney(
        sendMoneyBody
    )
}