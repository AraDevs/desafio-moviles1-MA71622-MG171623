package com.aradevs.desafio01_ma171622_mg171623.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aradevs.desafio01_ma171622_mg171623.R
import com.aradevs.desafio01_ma171622_mg171623.databinding.FragmentLoginBinding
import com.c3rberuss.androidutils.navigateOff

class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding: FragmentLoginBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            navigateOff(R.id.action_login_screen_to_bottom_nested_nav)
        }
    }
}