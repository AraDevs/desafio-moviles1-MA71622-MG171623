package com.aradevs.desafio01_ma171622_mg171623.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aradevs.domain.Status
import com.aradevs.domain.User
import com.aradevs.storagemanager.use_cases.GetUserUseCase
import com.aradevs.storagemanager.use_cases.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
) : ViewModel() {
}