package com.example.conciseweatherapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.futureprocessing.weatherapp.R
import com.futureprocessing.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navController by lazy {
        findNavController(R.id.navigationContainerView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.content.toolbar)
        setupDrawer()
    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            binding.root,
            binding.content.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.root.addDrawerListener(toggle)
        binding.content.toolbar.setNavigationOnClickListener {
            binding.root.open()
        }
        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()
    }

    override fun onBackPressed() {
        if (binding.root.isOpen) {
            binding.root.close()
        } else {
            super.onBackPressed()
        }
    }

}
