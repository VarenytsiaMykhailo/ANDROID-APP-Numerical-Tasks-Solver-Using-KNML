package com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.varenytsiamykhailo.numericaltaskssolver.databinding.ActivityOptimizationMethodsBinding
import com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.optimizationmethods.InputOptimizationDataActivity
import com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.optimizationmethods.InputSvennMethodDataActivity

class OptimizationMethodsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptimizationMethodsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptimizationMethodsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        binding.svennMethodInfoButton.setOnClickListener {
            val intent = Intent(this, MethodInfoActivity::class.java)
            intent.putExtra("methodName", "svennMethod")
            startActivity(intent)
        }
        binding.svennMethodButton.setOnClickListener {
            val intent = Intent(this, InputSvennMethodDataActivity::class.java)
            intent.putExtra("methodName", "svennMethod")
            startActivity(intent)
        }

        binding.goldenSectionMethodInfoButton.setOnClickListener {
            val intent = Intent(this, MethodInfoActivity::class.java)
            intent.putExtra("methodName", "goldenSectionMethod")
            startActivity(intent)
        }
        binding.goldenSectionMethodButton.setOnClickListener {
            val intent = Intent(this, InputOptimizationDataActivity::class.java)
            intent.putExtra("methodName", "goldenSectionMethod")
            startActivity(intent)
        }

        binding.fibonacciMethodInfoButton.setOnClickListener {
            val intent = Intent(this, MethodInfoActivity::class.java)
            intent.putExtra("methodName", "fibonacciMethod")
            startActivity(intent)
        }
        binding.fibonacciMethodButton.setOnClickListener {
            val intent = Intent(this, InputOptimizationDataActivity::class.java)
            intent.putExtra("methodName", "fibonacciMethod")
            startActivity(intent)
        }
    }

}