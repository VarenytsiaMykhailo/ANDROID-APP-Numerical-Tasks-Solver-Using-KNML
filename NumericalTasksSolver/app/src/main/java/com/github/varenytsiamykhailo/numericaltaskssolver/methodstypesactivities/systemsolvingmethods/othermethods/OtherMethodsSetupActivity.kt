package com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.systemsolvingmethods.othermethods

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.varenytsiamykhailo.knml.systemsolvingmethods.GaussMethod
import com.github.varenytsiamykhailo.knml.systemsolvingmethods.JacobiMethod
import com.github.varenytsiamykhailo.knml.systemsolvingmethods.SeidelMethod
import com.github.varenytsiamykhailo.knml.systemsolvingmethods.ThomasMethod
import com.github.varenytsiamykhailo.knml.util.results.VectorResultWithStatus

import com.github.varenytsiamykhailo.numericaltaskssolver.databinding.ActivityOtherMethodsSetupBinding
import com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.AnswerSolutionActivity

class OtherMethodsSetupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtherMethodsSetupBinding

    private lateinit var A: Array<Array<Double>>

    private lateinit var B: Array<Double>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtherMethodsSetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        A = this.intent.getSerializableExtra("matrixA") as Array<Array<Double>>
        B = this.intent.getSerializableExtra("vectorB") as Array<Double>

        setupViews()
    }

    private fun setupViews() {
        var formFullSolution: Boolean = false
        binding.formFullSolutionCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            formFullSolution = isChecked
        }

        binding.solveSystemButton.setOnClickListener {
            val methodName: String = this.intent.getStringExtra("methodName").toString()
            when (methodName) {
                "gaussSimpleMethod" -> {
                    val result: VectorResultWithStatus =
                        GaussMethod().solveSystemByGaussClassicMethod(
                            A,
                            B,
                            formSolution = formFullSolution
                        )

                    val resultString: String = formResultString(result)
                    startAnswerSolutionActivity(resultString)
                }
                "thomasMethod" -> {
                    val result: VectorResultWithStatus =
                        ThomasMethod().solveSystemByThomasMethod(
                            A,
                            B,
                            formSolution = formFullSolution
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
    }

    private fun formResultString(vectorResultWithStatus: VectorResultWithStatus): String {
        var resultString: String = ""
        resultString += "Is successful: " + vectorResultWithStatus.isSuccessful + "\n"
        if (vectorResultWithStatus.isSuccessful) {
            resultString += "Full solution: \n" + (vectorResultWithStatus.solutionObject?.solutionString
                ?: "not required.") + "\n\n"
            resultString += "Solution result: " + vectorResultWithStatus.vectorResult + "\n"
        } else {
            resultString += vectorResultWithStatus.errorException?.message ?: "Exception with null message"
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