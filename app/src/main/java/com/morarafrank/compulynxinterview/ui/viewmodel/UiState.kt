package com.morarafrank.compulynxinterview.ui.viewmodel

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success(val data: Boolean) : UiState()
    data class Error(val throwable: Throwable) : UiState()
}