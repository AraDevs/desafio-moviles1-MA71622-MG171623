package com.aradevs.desafio01_ma171622_mg171623.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aradevs.desafio01_ma171622_mg171623.R
import com.aradevs.desafio01_ma171622_mg171623.databinding.ActivityMainBinding
import com.c3rberuss.androidutils.gone
import com.c3rberuss.androidutils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(this::onDestinationChanged)
        binding.bottomNav.setupWithNavController(navController)
    }

    private fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        args: Bundle?,
    ) {
        when (destination.id) {
            R.id.login_screen, R.id.register_screen -> binding.bottomNav.gone()
            else -> binding.bottomNav.visible()
        }
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.e1_fragment, R.id.e2_fragment, R.id.e3_fragment -> finish()
            else -> {
                //do nothing
            }
        }
        super.onBackPressed()
    }
}