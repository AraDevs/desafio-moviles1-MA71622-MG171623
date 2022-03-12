package com.aradevs.desafio01_ma171622_mg171623.ui.e1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aradevs.desafio01_ma171622_mg171623.R
import com.aradevs.desafio01_ma171622_mg171623.databinding.FragmentE1Binding
import com.aradevs.desafio01_ma171622_mg171623.ui.utils.twoDecimals
import kotlin.math.pow
import kotlin.math.sqrt

class E1Fragment : Fragment(R.layout.fragment_e1) {
    private val binding: FragmentE1Binding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {

        binding.apply {
            calcButton.setOnClickListener {
                calcCuadratic()
            }
        }
    }

    private fun calcCuadratic() {
        binding.apply {
            val inputNameList = listOf(aValue, bValue, cValue)
            val inputsValid = inputNameList.any { it.text.isNullOrEmpty() }

            if (inputsValid) {
                Toast.makeText(context, "Por favor ingrese todas las variables", Toast.LENGTH_SHORT)
                    .show()
                return
            }

            val a = aValue.text.toString().toDouble()
            val b = bValue.text.toString().toDouble()
            val c = cValue.text.toString().toDouble()

            if (a == 0.0) {
                Toast.makeText(context, "La variable a no puede ser 0", Toast.LENGTH_SHORT)
                    .show()
                return
            }

            val root = b.pow(2) - (4 * a *c)
            val denom = 2 * a

            val x1: String
            val x2: String

            if (root >= 0) {
                x1 = ((- b + sqrt(root)) / denom).twoDecimals()
                x2 = ((- b - sqrt(root)) / denom).twoDecimals()
            } else {
                val real = - b / denom
                val imag = sqrt(- root) / denom

                x1 = real.twoDecimals() + " + " + imag.twoDecimals() + "i"
                x2 = real.twoDecimals() + " - " + imag.twoDecimals() + "i"
            }

            Toast.makeText(context, "Las raíces son: \nx1 = $x1 \nx2 = $x2", Toast.LENGTH_LONG)
                .show()
        }
    }
}