package com.aradevs.desafio01_ma171622_mg171623.ui

import androidx.lifecycle.ViewModel
import com.aradevs.storagemanager.use_cases.GetUserUseCase
import com.aradevs.storagemanager.use_cases.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
) : ViewModel(){
}