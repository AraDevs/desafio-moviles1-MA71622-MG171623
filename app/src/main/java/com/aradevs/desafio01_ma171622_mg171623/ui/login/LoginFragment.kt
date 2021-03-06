package com.aradevs.desafio01_ma171622_mg171623.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aradevs.desafio01_ma171622_mg171623.R
import com.aradevs.desafio01_ma171622_mg171623.databinding.FragmentLoginBinding
import com.aradevs.desafio01_ma171622_mg171623.ui.login.view_models.LoginViewModel
import com.aradevs.domain.Status
import com.c3rberuss.androidutils.navigate
import com.c3rberuss.androidutils.navigateOff
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding: FragmentLoginBinding by viewBinding()
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectAuth()

        with(binding) {
            loginButton.setOnClickListener {
                viewModel.getUser(username.text.toString(), password.text.toString())
            }
            registerButton.setOnClickListener {
                navigate(R.id.action_login_screen_to_register_screen)
            }
        }
    }

    private fun collectAuth() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchStatus.collect { status ->
                    when (status) {
                        is Status.Loading -> {
                            binding.loginButton.isEnabled = false
                        }
                        is Status.Success -> {
                            binding.loginButton.isEnabled = true
                            if (status.data == null) {
                                Snackbar.make(binding.loginButton,
                                    getString(R.string.auth_error),
                                    LENGTH_SHORT).show()
                                return@collect
                            }
                            navigateOff(R.id.action_login_screen_to_bottom_nested_nav)
                        }
                        is Status.Error -> {
                            Snackbar.make(binding.loginButton,
                                getString(R.string.unexpected_error),
                                LENGTH_SHORT).show()
                            binding.loginButton.isEnabled = true
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