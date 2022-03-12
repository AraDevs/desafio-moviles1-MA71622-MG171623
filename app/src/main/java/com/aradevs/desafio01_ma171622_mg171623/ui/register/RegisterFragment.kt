package com.aradevs.desafio01_ma171622_mg171623.ui.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aradevs.desafio01_ma171622_mg171623.R
import com.aradevs.desafio01_ma171622_mg171623.databinding.FragmentRegisterBinding
import com.aradevs.desafio01_ma171622_mg171623.ui.register.view_models.RegisterViewModel
import com.aradevs.domain.Status
import com.c3rberuss.androidutils.navigateOff
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {
    private val viewModel: RegisterViewModel by viewModels()
    private val binding: FragmentRegisterBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectAuth()

        with(binding) {
            registerButton.setOnClickListener {
                if (!username.text.isNullOrEmpty() && !password.text.isNullOrEmpty()) {
                    viewModel.saveUser(username.text.toString(), password.text.toString())
                }
            }
        }

    }

    private fun collectAuth() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchStatus.collect { status ->
                    when (status) {
                        is Status.Loading -> {
                            binding.registerButton.isEnabled = false
                        }
                        is Status.Success -> {
                            binding.registerButton.isEnabled = true
                            navigateOff(R.id.action_register_screen_to_bottom_nested_nav)
                        }
                        is Status.Error -> {
                            Snackbar.make(binding.registerButton,
                                getString(R.string.unexpected_error),
                                BaseTransientBottomBar.LENGTH_SHORT).show()
                            binding.registerButton.isEnabled = true
                        }
                        else -> {
                            //Do nothing
                        }
                    }
                }
            }
        }
    }
}