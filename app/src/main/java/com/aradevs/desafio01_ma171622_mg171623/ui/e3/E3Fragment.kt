package com.aradevs.desafio01_ma171622_mg171623.ui.e3

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aradevs.desafio01_ma171622_mg171623.R
import com.aradevs.desafio01_ma171622_mg171623.databinding.FragmentE3Binding
import com.aradevs.desafio01_ma171622_mg171623.ui.dialog.E3Dialog

class E3Fragment : Fragment(R.layout.fragment_e3) {
    private val binding: FragmentE3Binding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {

        binding.apply {
            ArrayAdapter.createFromResource(
                requireContext().applicationContext,
                R.array.position_items,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                position1.adapter = adapter
                position2.adapter = adapter
                position3.adapter = adapter
            }

            salaryButton.setOnClickListener {
                openDialog()
            }
        }
    }

    private fun calcSalary(hours: Int, position: String, noBonus: Boolean): SalaryInfo {
        val salary = if (hours <= 160) {
            hours * 9.75
        } else {
            val extraHours = hours - 160
            (160 * 9.75) + (extraHours * 11.5)
        }
        val isss = salary * 0.0525
        val afp = salary * 0.0688
        val rent = salary * 0.1
        val liquid = salary - isss - afp - rent
        val bonus = when (position) {
            "Gerente" -> liquid * 0.1
            "Asistente" -> liquid * 0.05
            "Secretaria" -> liquid * 0.03
            else -> liquid * 0.02
        }

        return SalaryInfo(
            "$" + String.format("%.2f", isss),
            "$" + String.format("%.2f", afp),
            "$" + String.format("%.2f", rent),
            "$" + String.format("%.2f", liquid),
            if (noBonus) "NO HAY BONO" else "$" + String.format("%.2f", bonus),
            salary + liquid
        )
    }

    private fun calcStats(
        salary: SalaryInfo,
        employee: String,
        stats: StatInfo
    ): StatInfo {
        var maxSalary = stats.maxSalary
        var maxSalaryHolder = stats.maxSalaryHolder
        var minSalary = stats.minSalary
        var minSalaryHolder = stats.minSalaryHolder
        var over300 = stats.over300

        if (salary.netSalary > maxSalary) {
            maxSalary = salary.netSalary
            maxSalaryHolder = employee
        }
        if (salary.netSalary < minSalary) {
            minSalary = salary.netSalary
            minSalaryHolder = employee
        }
        if (salary.netSalary > 300) {
            over300++
        }

        return StatInfo(maxSalary, maxSalaryHolder, minSalary, minSalaryHolder, over300)
    }

    private fun openDialog() {
        binding.apply {

            val inputNameList = listOf(name1, name2, name3, lastname1, lastname2, lastname3)
            val nameValid = inputNameList.any { it.text.isNullOrEmpty() }

            val inputHourList = listOf(hour1, hour2, hour3)
            val hourValid = inputHourList.any { it.text.isNullOrEmpty() || it.text.toString() == "0" }

            if (nameValid) {
                Toast.makeText(context, "Por favor ingrese todos los nombres", Toast.LENGTH_SHORT)
                    .show()
                return
            }

            if (hourValid) {
                Toast.makeText(
                    context,
                    "Por favor ingrese todas las horas trabajadas",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            val noBonus = position1.selectedItem.toString() == "Gerente"
                    && position2.selectedItem.toString() == "Asistente"
                    && position3.selectedItem.toString() == "Secretaria"

            val employee1 = name1.text.toString() + " " + lastname1.text.toString()
            val salary1 = calcSalary(
                hour1.text.toString().toInt(),
                position1.selectedItem.toString(),
                noBonus
            )
            val employee2 = name2.text.toString() + " " + lastname2.text.toString()
            val salary2 = calcSalary(
                hour2.text.toString().toInt(),
                position2.selectedItem.toString(),
                noBonus
            )
            val employee3 = name3.text.toString() + " " + lastname3.text.toString()
            val salary3 = calcSalary(
                hour3.text.toString().toInt(),
                position3.selectedItem.toString(),
                noBonus
            )

            var stats = StatInfo(0.0, "", Double.MAX_VALUE, "", 0)

            stats = calcStats(salary1, employee1, stats)
            stats = calcStats(salary2, employee2, stats)
            stats = calcStats(salary3, employee3, stats)

            E3Dialog.newInstance(
                employee1,
                salary1,
                employee2,
                salary2,
                employee3,
                salary3,
                stats.maxSalaryHolder,
                stats.minSalaryHolder,
                stats.over300
            ).show(childFragmentManager, "ConfirmDeleteAudioDialog")
        }
    }
}