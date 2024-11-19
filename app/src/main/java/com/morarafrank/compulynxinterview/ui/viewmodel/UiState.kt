package com.morarafrank.compulynxinterview.ui.viewmodel

import com.morarafrank.compulynxinterview.data.remote.model.LoginResponse
import com.morarafrank.compulynxinterview.utils.Resource

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success(val data: Boolean) : UiState()
    data class Error(val throwable: Throwable) : UiState()
}