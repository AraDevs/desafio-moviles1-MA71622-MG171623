package com.aradevs.desafio01_ma171622_mg171623.ui.e2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aradevs.desafio01_ma171622_mg171623.R
import com.aradevs.desafio01_ma171622_mg171623.databinding.FragmentE2Binding
import twoDecimals
import java.math.BigDecimal
import java.math.RoundingMode

class E2Fragment : Fragment(R.layout.fragment_e2) {
    private val binding: FragmentE2Binding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {

        binding.apply {
            calcButton.setOnClickListener {
                countVotes()
            }
        }
    }

    private fun countVotes() {
        binding.apply {
            if (candidates.text.isNullOrEmpty()) {
                Toast.makeText(context, "Por favor ingrese el formato de votos", Toast.LENGTH_SHORT)
                    .show()
                return
            }

            val voteString = candidates.text.toString()
            val votesInput = voteString.split(",")
            val parsedVotes: MutableList<CandidatesInfo> = mutableListOf()

            val total = votesInput.size

            for (candidateInput in votesInput) {
                val candidateNumber = candidateInput.toIntOrNull()
                if (candidateNumber == null) {
                    Toast.makeText(context, "Todos los valores ingresados deben ser enteros", Toast.LENGTH_SHORT)
                        .show()
                    return
                }
                if (candidateNumber < 1) {
                    Toast.makeText(context, "Todos los valores ingresados deben ser mayores a 0", Toast.LENGTH_SHORT)
                        .show()
                    return
                }

                var candidate = parsedVotes.firstOrNull { it.number == candidateInput }

                if (candidate == null) {
                    candidate = CandidatesInfo(candidateInput, 0, 0.0)
                    parsedVotes.add(candidate)
                }

                val votes = candidate.votes + 1
                val percentage = votes.toDouble() / total * 100

                parsedVotes[parsedVotes.indexOf(candidate)] = CandidatesInfo(candidateInput, votes, percentage)
            }

            var output = "Los resultados fueron: "

            for (candidate in parsedVotes) {
                output += "\nCandidato #" + candidate.number + ": " + candidate.votes + " votos - " + candidate.percentage.twoDecimals() + "%"
            }

            Toast.makeText(context, output, Toast.LENGTH_LONG).show()
        }
    }

}