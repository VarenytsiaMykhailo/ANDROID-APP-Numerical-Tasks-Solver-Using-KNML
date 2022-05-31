package com.github.varenytsiamykhailo.numericaltaskssolver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.varenytsiamykhailo.numericaltaskssolver.databinding.ActivityMainBinding
import com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.IntegralMethodsActivity
import com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.SystemSolvingMethodsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        binding.mainActivityIntegralMethodsButton.setOnClickListener {
            val intent = Intent(this, IntegralMethodsActivity::class.java)
            startActivity(intent)
        }

        binding.mainActivitySystemSolvingMethodsButton.setOnClickListener {
            val intent = Intent(this, SystemSolvingMethodsActivity::class.java)
            startActivity(intent)
        }
    }

}