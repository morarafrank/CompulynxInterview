package com.morarafrank.compulynxinterview.domain.use_cases

import com.morarafrank.compulynxinterview.data.remote.model.SendMoneyBody
import com.morarafrank.compulynxinterview.domain.repo.CompulynxRepository
import javax.inject.Inject

class SendMoneyUseCase @Inject constructor(
    private val repository: CompulynxRepository
){
    suspend operator fun invoke(
        sendMoneyBody: SendMoneyBody
    ) = repository.sendMoney(
        sendMoneyBody
    )
}