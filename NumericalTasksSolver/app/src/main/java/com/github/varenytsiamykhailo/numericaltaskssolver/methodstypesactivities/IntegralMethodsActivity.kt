package com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.varenytsiamykhailo.numericaltaskssolver.databinding.ActivityIntegralMethodsBinding
import com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.integralsolvingmethods.InputIntegralDataActivity

class IntegralMethodsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntegralMethodsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntegralMethodsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        binding.rectangleMethodInfoButton.setOnClickListener {
            val intent = Intent(this, MethodInfoActivity::class.java)
            intent.putExtra("methodName", "rectangleMethod")
            startActivity(intent)
        }
        binding.rectangleMethodButton.setOnClickListener {
            val intent = Intent(this, InputIntegralDataActivity::class.java)
            intent.putExtra("methodName", "rectangleMethod")
            startActivity(intent)
        }

        binding.trapezoidMethodInfoButton.setOnClickListener {
            val intent = Intent(this, MethodInfoActivity::class.java)
            intent.putExtra("methodName", "trapezoidMethod")
            startActivity(intent)
        }
        binding.trapezoidMethodButton.setOnClickListener {
            val intent = Intent(this, InputIntegralDataActivity::class.java)
            intent.putExtra("methodName", "trapezoidMethod")
            startActivity(intent)
        }

        binding.simpsonMethodInfoButton.setOnClickListener {
            val intent = Intent(this, MethodInfoActivity::class.java)
            intent.putExtra("methodName", "simpsonMethod")
            startActivity(intent)
        }
        binding.simpsonMethodButton.setOnClickListener {
            val intent = Intent(this, InputIntegralDataActivity::class.java)
            intent.putExtra("methodName", "simpsonMethod")
            startActivity(intent)
        }
    }

}