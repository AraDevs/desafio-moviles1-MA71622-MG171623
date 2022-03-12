package com.aradevs.desafio01_ma171622_mg171623.ui.e3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SalaryInfo(
    val isss: String,
    val afp: String,
    val rent: String,
    val liquid: String,
    val bonus: String,
    val netSalary: Double
    ): Parcelable