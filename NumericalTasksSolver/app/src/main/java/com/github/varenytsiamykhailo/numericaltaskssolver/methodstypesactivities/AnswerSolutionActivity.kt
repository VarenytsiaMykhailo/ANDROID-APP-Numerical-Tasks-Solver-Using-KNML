package com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.github.varenytsiamykhailo.numericaltaskssolver.databinding.ActivityAnswerSolutionBinding

class AnswerSolutionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnswerSolutionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerSolutionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        val resultString: String = this.intent.getStringExtra("resultString").toString()
        binding.solutionTextView.text = resultString

        binding.textScaleSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.textScaleTextView.text = "Scale: $progress"
                val mult: Float = 1F + progress.toFloat() / 100
                binding.solutionTextView.setLineSpacing(0F, mult)
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