package com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.systemsolvingmethods

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.*
import android.util.TypedValue
import com.github.varenytsiamykhailo.numericaltaskssolver.databinding.ActivityInputSystemDataBinding
import android.widget.EditText
import android.widget.GridLayout
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.systemsolvingmethods.jacobiseidelmethods.JacobiSeidelMethodsSetupActivity


class InputSystemDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputSystemDataBinding

    private val MIN_NUM_OF_ROWS: Int = 1

    private val MAX_NUM_OF_ROWS: Int = 30

    private val MIN_NUM_OF_COLS: Int = 1

    private val MAX_NUM_OF_COLS: Int = 30

    private val DEFAULT_MATRIX_A: Array<Array<Double>> = arrayOf(
        arrayOf(115.0, -20.0, -75.0),
        arrayOf(15.0, -50.0, -5.0),
        arrayOf(6.0, 2.0, 20.0)
    )

    private val DEFAULT_VECTOR_B: Array<Double> = arrayOf(20.0, -40.0, 28.0)

    private lateinit var editTextsOfGridLayoutOfMatrixA: Array<Array<EditText>>

    private lateinit var editTextsOfGridLayoutOfVectorB: Array<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputSystemDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    @SuppressLint("SetTextI18n")
    private fun setupViews() {

        binding.numOfRowsEditText.hint = "$MIN_NUM_OF_ROWS - $MAX_NUM_OF_ROWS"
        binding.numOfColumnsEditText.hint = "$MIN_NUM_OF_COLS - $MAX_NUM_OF_COLS"

        val methodName: String = this.intent.getStringExtra("methodName").toString()
        binding.chosenMethodTextView.text = "Chosen method: " + when (methodName) {
            "gaussSimpleMethod" -> {
                "Gauss simple method"
            }
            "thomasMethod" -> {
                "Thomas method"
            }
            "jacobiMethod" -> {
                "Jacobi method"
            }
            "seidelMethod" -> {
                "Seidel method"
            }
            else -> {
                "incorrect method type chosen"
            }
        }

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

        binding.solveSystemButton.setOnClickListener {

            val n: Int = editTextsOfGridLayoutOfMatrixA.size
            val m: Int = editTextsOfGridLayoutOfMatrixA[0].size

            val A: Array<Array<Double>> = Array(n) {
                Array<Double>(m) { 0.0 }
            }

            for (i in 0 until n) {
                for (j in 0 until m) {
                    try {
                        A[i][j] = editTextsOfGridLayoutOfMatrixA[i][j].text.toString().toDouble()
                    } catch (e: NumberFormatException) {
                        Toast.makeText(
                            this, "Incorrect value at A[$i][$j]. Expected double/float type.",
                            Toast.LENGTH_LONG
                        ).show()

                        return@setOnClickListener
                    }
                }
            }

            val B: Array<Double> = Array(n) { 0.0 }

            for (i in 0 until n) {
                try {
                    B[i] = editTextsOfGridLayoutOfVectorB[i].text.toString().toDouble()
                } catch (e: NumberFormatException) {
                    Toast.makeText(
                        this, "Incorrect value at B[$i]. Expected double/float type.",
                        Toast.LENGTH_LONG
                    ).show()

                    return@setOnClickListener
                }
            }


            val methodName: String = this.intent.getStringExtra("methodName").toString()
            when (methodName) {
                "gaussSimpleMethod" -> {

                }
                "thomasMethod" -> {

                }
                "jacobiMethod" -> {
                    val intent = Intent(
                        this@InputSystemDataActivity,
                        JacobiSeidelMethodsSetupActivity::class.java
                    )
                    intent.putExtra("methodName", "jacobiMethod")
                    intent.putExtra("matrixA", A)
                    intent.putExtra("vectorB", B)
                    startActivity(intent)
                }
                "seidelMethod" -> {
                    val intent = Intent(
                        this@InputSystemDataActivity,
                        JacobiSeidelMethodsSetupActivity::class.java
                    )
                    intent.putExtra("methodName", "seidelMethod")
                    intent.putExtra("matrixA", A)
                    intent.putExtra("vectorB", B)
                    startActivity(intent)
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
        val numOfRows: Int = DEFAULT_MATRIX_A.size
        val numOfCols: Int = DEFAULT_MATRIX_A[0].size

        binding.numOfRowsEditText.setText(numOfRows.toString())
        binding.numOfColumnsEditText.setText(numOfCols.toString())

        buildGridLayoutsOfMatrixAVectorB(numOfRows, numOfCols)

        for (i in 0 until numOfRows) {
            for (j in 0 until numOfCols) {
                editTextsOfGridLayoutOfMatrixA[i][j].setText(DEFAULT_MATRIX_A[i][j].toString())
            }
        }

        for (i in 0 until numOfRows) {
            editTextsOfGridLayoutOfVectorB[i].setText(DEFAULT_VECTOR_B[i].toString())
        }
    }

    private fun controlMinMaxValuesOfEditText(editText: EditText, minValue: Int, maxValue: Int) {
        val str: String = editText.text.toString()
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

        editTextsOfGridLayoutOfMatrixA = Array(numOfRows) {
            Array(numOfCols) {
                EditText(gridLayoutOfMatrixA.context)
            }
        }

        for (i in 0 until numOfRows) {
            for (j in 0 until numOfCols) {
                //editTexts[i][j] = EditText(gridLayoutOfMatrixA.context)
                gridLayoutOfMatrixA.addView(editTextsOfGridLayoutOfMatrixA[i][j])
                setupEditText(editTextsOfGridLayoutOfMatrixA[i][j], i, j)
            }
        }
    }

    private fun buildGridLayoutOfVectorB(numOfRows: Int) {
        val gridLayoutOfVectorB = binding.gridLayoutOfVectorB

        gridLayoutOfVectorB.removeAllViews()

        gridLayoutOfVectorB.rowCount = numOfRows
        gridLayoutOfVectorB.columnCount = 1

        editTextsOfGridLayoutOfVectorB = Array(numOfRows) {
            EditText(gridLayoutOfVectorB.context)
        }

        for (i in 0 until numOfRows) {
            //editTexts[i] = EditText(gridLayoutOfVectorB.context)
            gridLayoutOfVectorB.addView(editTextsOfGridLayoutOfVectorB[i])
            setupEditText(editTextsOfGridLayoutOfVectorB[i], i, 0)
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