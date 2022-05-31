package com.github.varenytsiamykhailo.numericaltaskssolver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.varenytsiamykhailo.knml.systemsolvingmethods.SeidelMethod
import com.github.varenytsiamykhailo.knml.util.results.VectorResultWithStatus
import com.github.varenytsiamykhailo.numericaltaskssolver.databinding.ActivityMainBinding
import com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.IntegralMethodsActivity
import com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.SystemSolvingMethodsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainActivityIntegralMethodsButton.setOnClickListener {
            val intent = Intent(this@MainActivity, IntegralMethodsActivity::class.java)
            startActivity(intent)
        }

        binding.mainActivitySystemSolvingMethodsButton.setOnClickListener {
            val intent = Intent(this@MainActivity, SystemSolvingMethodsActivity::class.java)
            startActivity(intent)
        }

        val A: Array<Array<Double>> = arrayOf(
            arrayOf(115.0, -20.0, -75.0),
            arrayOf(15.0, -50.0, -5.0),
            arrayOf(6.0, 2.0, 20.0)
        )
        val B: Array<Double> = arrayOf(20.0, -40.0, 28.0)


        val result: VectorResultWithStatus = SeidelMethod().solveSystemBySeidelMethod(
            A,
            B,
            initialApproximation = Array(3) { 1.0 },
            eps = 0.01,
            formSolution = true
        )


        /*
        println(result.vectorResult)
        println(result.isSuccessful)
        println(result.errorException)
        println(result.solutionObject!!.solutionString)

         */
    }

}