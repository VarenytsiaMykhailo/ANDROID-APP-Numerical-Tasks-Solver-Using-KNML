package com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.varenytsiamykhailo.numericaltaskssolver.databinding.ActivitySystemSolvingMethodsBinding
import com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.systemsolvingmethods.InputSystemDataActivity

class SystemSolvingMethodsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySystemSolvingMethodsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySystemSolvingMethodsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        binding.gaussSimpleMethodButton.setOnClickListener {
            val intent = Intent(this@SystemSolvingMethodsActivity, InputSystemDataActivity::class.java)
            intent.putExtra("methodName", "gaussSimpleMethod")
            startActivity(intent)
        }

        binding.thomasMethodButton.setOnClickListener {
            val intent = Intent(this@SystemSolvingMethodsActivity, InputSystemDataActivity::class.java)
            intent.putExtra("methodName", "thomasMethod")
            startActivity(intent)
        }

        binding.jacobiMethodButton.setOnClickListener {
            val intent = Intent(this@SystemSolvingMethodsActivity, InputSystemDataActivity::class.java)
            intent.putExtra("methodName", "jacobiMethod")
            startActivity(intent)
        }

        binding.seidelMethodButton.setOnClickListener {
            val intent = Intent(this@SystemSolvingMethodsActivity, InputSystemDataActivity::class.java)
            intent.putExtra("methodName", "seidelMethod")
            startActivity(intent)
        }

    }

}