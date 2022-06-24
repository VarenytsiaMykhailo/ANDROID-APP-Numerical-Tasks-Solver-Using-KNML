package com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.integralsolvingmethods

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.varenytsiamykhailo.knml.integralmethods.RectangleMethod
import com.github.varenytsiamykhailo.knml.integralmethods.SimpsonMethod
import com.github.varenytsiamykhailo.knml.integralmethods.TrapezoidMethod
import com.github.varenytsiamykhailo.knml.util.results.DoubleResultWithStatus
import com.github.varenytsiamykhailo.numericaltaskssolver.databinding.ActivityInputIntegralDataBinding
import com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.AnswerSolutionActivity
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class InputIntegralDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputIntegralDataBinding

    private val DEFAULT_EPS: String = "0.0001"

    private val DEFAULT_INTERVAL_START: String = "0.0"

    private val DEFAULT_INTERVAL_END: String = "1.0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputIntegralDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    @SuppressLint("SetTextI18n")
    private fun setupViews() {
        val methodName: String = this.intent.getStringExtra("methodName").toString()
        binding.chosenMethodTextView.text = "Chosen method: " + when (methodName) {
            "rectangleMethod" -> {
                "rectangle method"
            }
            "trapezoidMethod" -> {
                "Trapezoid method"
            }
            "simpsonMethod" -> {
                "Simpson method"
            }
            else -> {
                "incorrect method type chosen"
            }
        }

        binding.epsEditText.setText(DEFAULT_EPS)

        var useMachineEps: Boolean = false

        binding.useMachineEpsCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                useMachineEps = true
                binding.epsEditText.setText("0")
            } else {
                useMachineEps = false
                binding.epsEditText.setText(DEFAULT_EPS)
            }
        }

        var formFullSolution: Boolean = false
        binding.formFullSolutionCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            formFullSolution = isChecked
        }

        binding.solveIntegralButton.setOnClickListener {

            val functionString: String = binding.functionEditText.text.toString()
            val functionExpression: Expression = try {
                ExpressionBuilder(functionString).variables("x").build()
            } catch (e: Exception) {
                Toast.makeText(
                    this, "Incorrect function." + e.message,
                    Toast.LENGTH_LONG
                ).show()

                return@setOnClickListener
            }

            val function: (x: Double) -> Double = { x ->
                try {
                    functionExpression.setVariable("x", x).evaluate()
                } catch (e: Throwable) {
                    if (e.message == "Division by zero!") {
                        Double.POSITIVE_INFINITY
                    } else {
                        Double.NaN
                    }
                }
            }


            val intervalStart: Double = try {
                binding.intervalStartEditText.text.toString().toDouble()
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    this, "Incorrect interval start value. Expected double/float type.",
                    Toast.LENGTH_LONG
                ).show()

                return@setOnClickListener
            }

            val intervalEnd: Double = try {
                binding.intervalEndEditText.text.toString().toDouble()
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    this, "Incorrect interval end value. Expected double/float type.",
                    Toast.LENGTH_LONG
                ).show()

                return@setOnClickListener
            }

            var eps: Double = DEFAULT_EPS.toDouble()
            try {
                if (!useMachineEps) {
                    eps = binding.epsEditText.text.toString().toDouble()
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    this, "Incorrect eps value. Expected double/float type.",
                    Toast.LENGTH_LONG
                ).show()

                return@setOnClickListener
            }


            val methodName: String = this.intent.getStringExtra("methodName").toString()
            when (methodName) {
                "rectangleMethod" -> {
                    val result: DoubleResultWithStatus =
                        RectangleMethod().solveIntegralByRectangleMethod(
                            intervalStart,
                            intervalEnd,
                            eps,
                            formFullSolution,
                            function
                        )

                    val resultString: String = formResultString(result)
                    startAnswerSolutionActivity(resultString)
                }
                "trapezoidMethod" -> {
                    val result: DoubleResultWithStatus =
                        TrapezoidMethod().solveIntegralByTrapezoidMethod(
                            intervalStart,
                            intervalEnd,
                            eps,
                            formFullSolution,
                            function
                        )

                    val resultString: String = formResultString(result)
                    startAnswerSolutionActivity(resultString)
                }
                "simpsonMethod" -> {
                    val result: DoubleResultWithStatus =
                        SimpsonMethod().solveIntegralBySimpsonMethod(
                            intervalStart,
                            intervalEnd,
                            eps,
                            formFullSolution,
                            function
                        )

                    val resultString: String = formResultString(result)
                    startAnswerSolutionActivity(resultString)
                }
                else -> {
                    Toast.makeText(
                        this, "Incorrect method type chosen.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        setupDefaultValuesInGrids()
    }

    private fun setupDefaultValuesInGrids() {
        binding.intervalStartEditText.setText(DEFAULT_INTERVAL_START)
        binding.intervalEndEditText.setText(DEFAULT_INTERVAL_END)
    }

    private fun formResultString(doubleResultWithStatus: DoubleResultWithStatus): String {
        var resultString: String = ""
        resultString += "Is successful: " + doubleResultWithStatus.isSuccessful + "\n"
        if (doubleResultWithStatus.isSuccessful) {
            resultString += "Full solution: \n" + (doubleResultWithStatus.solutionObject?.solutionString
                ?: "not required.") + "\n\n"
            resultString += "Solution result: " + doubleResultWithStatus.doubleResult + "\n"
        } else {
            resultString += doubleResultWithStatus.errorException?.message
                ?: "Exception with null message"
        }
        return resultString
    }

    private fun startAnswerSolutionActivity(resultString: String) {
        val intent = Intent(
            this,
            AnswerSolutionActivity::class.java
        )
        intent.putExtra("resultString", resultString)
        startActivity(intent)
    }

}