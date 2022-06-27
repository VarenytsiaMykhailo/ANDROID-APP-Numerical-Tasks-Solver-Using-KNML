package com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.SeekBar
import com.github.varenytsiamykhailo.numericaltaskssolver.R
import com.github.varenytsiamykhailo.numericaltaskssolver.databinding.ActivityMethodInfoBinding

class MethodInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMethodInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMethodInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {

        // Hyperlinks support
        //binding.descriptionTextView.setMovementMethod(LinkMovementMethod.getInstance());
        //binding.descriptionTextView.setLinkTextColor(Color.BLUE)

        val methodName: String = this.intent.getStringExtra("methodName").toString()
        val resultString: String = when (methodName) {
            "gaussSimpleMethod" -> {
                getString(R.string.gauss_classic_method_description)
            }
            "thomasMethod" -> {
                getString(R.string.thomas_method_description)
            }
            "jacobiMethod" -> {
                getString(R.string.jacobi_method_description)
            }
            "seidelMethod" -> {
                getString(R.string.seidel_method_description)
            }
            "rectangleMethod" -> {
                getString(R.string.rectangle_method_description)
            }
            "trapezoidMethod" -> {
                getString(R.string.trapezoid_method_description)
            }
            "simpsonMethod" -> {
                getString(R.string.simpson_method_description)
            }
            "svennMethod" -> {
                getString(R.string.svenn_method_description)
            }
            "goldenSectionMethod" -> {
                getString(R.string.golden_section_method_description)
            }
            "fibonacciMethod" -> {
                getString(R.string.fibonacci_method_description)
            }
            else -> {
                "Incorrect method name chosen."
            }
        }


        binding.descriptionTextView.text = resultString

        binding.textScaleSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.textScaleTextView.text = "Scale: $progress"
                val mult: Float = 1F + progress.toFloat() / 100
                binding.descriptionTextView.setLineSpacing(0F, mult)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // TODO
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // TODO
            }
        })
    }
}