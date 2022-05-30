package com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.systemsolvingmethods

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.*
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.util.TypedValue
import com.github.varenytsiamykhailo.numericaltaskssolver.databinding.ActivityGaussSimpleMethodBinding
import android.widget.EditText
import android.widget.GridLayout
import android.view.Gravity
import android.view.View


class GaussSimpleMethodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGaussSimpleMethodBinding

    private val MIN_NUM_OF_ROWS: Int = 1

    private val MAX_NUM_OF_ROWS: Int = 30

    private val MIN_NUM_OF_COLS: Int = 1

    private val MAX_NUM_OF_COLS: Int = 30


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGaussSimpleMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var numOfRows: Int = 4
        var numOfCols: Int = 4

        buildGridLayoutsOfMatrixAVectorB(numOfRows, numOfCols)

        setupViews()
    }

    private fun setupViews() {

        binding.numOfRowsEditText.hint = "$MIN_NUM_OF_ROWS - $MAX_NUM_OF_ROWS"
        binding.numOfColumnsEditText.hint = "$MIN_NUM_OF_COLS - $MAX_NUM_OF_COLS"

        binding.numOfRowsEditText.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                if (!hasFocus) {
                    controlMinMaxValuesOfEditText(
                        view as EditText,
                        MIN_NUM_OF_ROWS,
                        MAX_NUM_OF_ROWS
                    )
                    //buildGridLayoutOfMatrixA(numOfRows, numOfCols)
                    //editText.clearFocus()
                }
            }

        binding.numOfColumnsEditText.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                if (!hasFocus) {
                    controlMinMaxValuesOfEditText(
                        view as EditText,
                        MIN_NUM_OF_COLS,
                        MAX_NUM_OF_COLS
                    )
                    //buildGridLayoutOfMatrixA(numOfRows, numOfCols)
                    //view.clearFocus()
                }
            }


        binding.refreshButton.setOnClickListener {
            controlMinMaxValuesOfEditText(
                binding.numOfRowsEditText,
                MIN_NUM_OF_ROWS,
                MAX_NUM_OF_ROWS
            )
            controlMinMaxValuesOfEditText(
                binding.numOfColumnsEditText,
                MIN_NUM_OF_COLS,
                MAX_NUM_OF_COLS
            )

            val numOfRows: Int = Integer.parseInt(binding.numOfRowsEditText.text.toString())
            val numOfCols: Int = Integer.parseInt(binding.numOfColumnsEditText.text.toString())

            buildGridLayoutsOfMatrixAVectorB(numOfRows, numOfCols)
        }
    }

    private fun controlMinMaxValuesOfEditText(editText: EditText, minValue: Int, maxValue: Int) {
        var str: String = editText.text.toString()
        if (str.isEmpty()) {
            editText.setText(minValue.toString())
            //editText.setSelection(editText.length())//placing cursor at the end of the text
        } else {
            val n = Integer.parseInt(str)
            if (n < minValue) {
                editText.setText(minValue.toString())
            } else if (n > maxValue) {
                editText.setText(maxValue.toString())
            }
        }
    }

    private fun buildGridLayoutsOfMatrixAVectorB(numOfRows: Int, numOfCols: Int) {
        buildGridLayoutOfMatrixA(numOfRows, numOfCols)
        buildGridLayoutOfVectorB(numOfRows)
    }

    private fun buildGridLayoutOfMatrixA(numOfRows: Int, numOfCols: Int) {
        val gridLayoutOfMatrixA = binding.gridLayoutOfMatrixA

        gridLayoutOfMatrixA.removeAllViews()

        gridLayoutOfMatrixA.rowCount = numOfRows
        gridLayoutOfMatrixA.columnCount = numOfCols

        val editTexts = Array(numOfRows) {
            Array(numOfCols) {
                EditText(gridLayoutOfMatrixA.context)
            }
        }

        for (i in 0 until numOfRows) {
            for (j in 0 until numOfCols) {
                //editTexts[i][j] = EditText(gridLayoutOfMatrixA.context)
                gridLayoutOfMatrixA.addView(editTexts[i][j])
                setupEditText(editTexts[i][j], i, j)
            }
        }
    }

    private fun buildGridLayoutOfVectorB(numOfRows: Int) {
        val gridLayoutOfVectorB = binding.gridLayoutOfVectorB

        gridLayoutOfVectorB.removeAllViews()

        gridLayoutOfVectorB.rowCount = numOfRows
        gridLayoutOfVectorB.columnCount = 1

        val editTexts = Array(numOfRows) {
            EditText(gridLayoutOfVectorB.context)
        }

        for (i in 0 until numOfRows) {
            //editTexts[i] = EditText(gridLayoutOfVectorB.context)
            gridLayoutOfVectorB.addView(editTexts[i])
            setupEditText(editTexts[i], i, 0)
        }
    }

    // Putting the edit text according to row and column index
    private fun setupEditText(editText: EditText, row: Int, column: Int) {
        val param = GridLayout.LayoutParams()
        param.width = 160
        param.height = 120
        param.setGravity(Gravity.CENTER)
        param.rowSpec = GridLayout.spec(row)
        param.columnSpec = GridLayout.spec(column)
        editText.layoutParams = param
        //editText.setText("${row}")
        editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
        editText.inputType =
            (InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
    }

}