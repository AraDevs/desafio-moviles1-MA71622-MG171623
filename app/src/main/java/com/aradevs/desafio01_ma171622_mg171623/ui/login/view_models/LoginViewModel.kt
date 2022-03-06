package com.aradevs.desafio01_ma171622_mg171623.ui.login.view_models

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
class LoginViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
) : ViewModel() {
    private val _fetchStatus: MutableStateFlow<Status<User?>> = MutableStateFlow(Status.Initial())
    val fetchStatus: StateFlow<Status<User?>> get() = _fetchStatus

    fun getUser(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _fetchStatus.emit(Status.Loading())
            when (val user = getUserUseCase(username, password)) {
                is Status.Initial, is Status.Loading -> {
                    //do nothing
                }
                is Status.Success -> {
                    if(user.data == null){
                        Timber.d("Not found and success")
                    }
                    Timber.d("found ${user.data?.username}")
                    _fetchStatus.emit(user)
                }
                is Status.Error -> {
                    Timber.d("Not found")
                    _fetchStatus.emit(Status.Error(user.exception))
                }
            }
        }
    }

    fun saveUser(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _fetchStatus.emit(Status.Loading())
            when (val user = saveUserUseCase(username, password)) {
                is Status.Initial, is Status.Loading -> {
                    //do nothing
                }
                is Status.Success -> {
                    Timber.d("Saved")
                    //_fetchStatus.emit(Status.Success(Unit))
                }
                is Status.Error -> {
                    Timber.d("Not saved ${user.exception}")
                    //_fetchStatus.emit(Status.Error(user.exception))
                }
            }
        }
    }
}