package com.aradevs.desafio01_ma171622_mg171623.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.aradevs.desafio01_ma171622_mg171623.R
import com.aradevs.desafio01_ma171622_mg171623.databinding.DialogE3Binding
import com.aradevs.desafio01_ma171622_mg171623.ui.e3.SalaryInfo

class E3Dialog : BaseDialogFragment() {

    private var employee1: String = ""
    private lateinit var salary1: SalaryInfo
    private var employee2: String = ""
    private lateinit var salary2: SalaryInfo
    private var employee3: String = ""
    private lateinit var salary3: SalaryInfo
    private var max: String = ""
    private var min: String = ""
    private var over300: Int = 0
    private lateinit var binding: DialogE3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            employee1 = it.getString("employee1", "")
            salary1 = it.getParcelable("salary1")!!
            employee2 = it.getString("employee2", "")
            salary2 = it.getParcelable("salary2")!!
            employee3 = it.getString("employee3", "")
            salary3 = it.getParcelable("salary3")!!
            max = it.getString("max", "")
            min = it.getString("min", "")
            over300 = it.getInt("over300")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogE3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.employee1.text = getString(R.string.employee, employee1)
        binding.isss1.text = getString(R.string.isss, salary1.isss)
        binding.afp1.text = getString(R.string.afp, salary1.afp)
        binding.rent1.text = getString(R.string.rent, salary1.rent)
        binding.liquid1.text = getString(R.string.liquid, salary1.liquid)
        binding.bonus1.text = getString(R.string.bonus, salary1.bonus)
        binding.employee2.text = getString(R.string.employee, employee2)
        binding.isss2.text = getString(R.string.isss, salary2.isss)
        binding.afp2.text = getString(R.string.afp, salary2.afp)
        binding.rent2.text = getString(R.string.rent, salary2.rent)
        binding.liquid2.text = getString(R.string.liquid, salary2.liquid)
        binding.bonus2.text = getString(R.string.bonus, salary2.bonus)
        binding.employee3.text = getString(R.string.employee, employee3)
        binding.isss3.text = getString(R.string.isss, salary3.isss)
        binding.afp3.text = getString(R.string.afp, salary3.afp)
        binding.rent3.text = getString(R.string.rent, salary3.rent)
        binding.liquid3.text = getString(R.string.liquid, salary3.liquid)
        binding.bonus3.text = getString(R.string.bonus, salary3.bonus)
        binding.max.text = getString(R.string.max, max)
        binding.min.text = getString(R.string.min, min)
        binding.over300.text = getString(R.string.over300, over300)

        binding.dismiss.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        fun newInstance(
            employee1: String,
            salary1: SalaryInfo,
            employee2: String,
            salary2: SalaryInfo,
            employee3: String,
            salary3: SalaryInfo,
            max: String,
            min: String,
            over300: Int,
        ): E3Dialog {
            return E3Dialog().apply {
                arguments = bundleOf(
                    "employee1" to employee1,
                    "salary1" to salary1,
                    "employee2" to employee2,
                    "salary2" to salary2,
                    "employee3" to employee3,
                    "salary3" to salary3,
                    "max" to max,
                    "min" to min,
                    "over300" to over300,
                )
            }
        }
    }
}