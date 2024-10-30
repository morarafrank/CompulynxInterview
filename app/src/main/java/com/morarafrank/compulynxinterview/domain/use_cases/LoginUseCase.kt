package com.morarafrank.compulynxinterview.domain.use_cases

import com.morarafrank.compulynxinterview.data.remote.model.LoginBody
import com.morarafrank.compulynxinterview.domain.repo.CompulynxRepository

class LoginUseCase(
    private val repository: CompulynxRepository
) {

    suspend operator fun invoke(
        loginBody: LoginBody
    ) = repository.login(
        loginBody
    )
}