package com.aradevs.desafio01_ma171622_mg171623.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aradevs.desafio01_ma171622_mg171623.R
import com.aradevs.desafio01_ma171622_mg171623.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //do something

        with(binding){
            add.setOnClickListener {
                viewModel.saveUser("test", "test")
            }
            get.setOnClickListener {
                viewModel.getUser("test","test")
            }
        }
    }
}