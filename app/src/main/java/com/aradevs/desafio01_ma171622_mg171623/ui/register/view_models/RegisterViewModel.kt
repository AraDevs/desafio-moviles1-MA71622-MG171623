package com.aradevs.desafio01_ma171622_mg171623.ui.register.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aradevs.domain.Status
import com.aradevs.domain.User
import com.aradevs.storagemanager.use_cases.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val saveUserUseCase: SaveUserUseCase) : ViewModel() {
    private val _fetchStatus: MutableStateFlow<Status<Unit>> = MutableStateFlow(Status.Initial())
    val fetchStatus: StateFlow<Status<Unit>> get() = _fetchStatus

    fun saveUser(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _fetchStatus.emit(Status.Loading())
            when (val user = saveUserUseCase(username, password)) {
                is Status.Initial, is Status.Loading -> {
                    //do nothing
                }
                is Status.Success -> {
                    _fetchStatus.emit(user)
                }
                is Status.Error -> {
                    _fetchStatus.emit(Status.Error(user.exception))
                }
            }
        }
    }
}