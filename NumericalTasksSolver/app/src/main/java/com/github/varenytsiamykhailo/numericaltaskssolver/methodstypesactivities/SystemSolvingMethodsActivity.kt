package com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.varenytsiamykhailo.numericaltaskssolver.databinding.ActivitySystemSolvingMethodsBinding
import com.github.varenytsiamykhailo.numericaltaskssolver.methodstypesactivities.systemsolvingmethods.GaussSimpleMethodActivity

class SystemSolvingMethodsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySystemSolvingMethodsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySystemSolvingMethodsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.systemSolvingMethodsActivityGaussSimpleMethodButton.setOnClickListener {
            val intent = Intent(this@SystemSolvingMethodsActivity, GaussSimpleMethodActivity::class.java)
            startActivity(intent)
        }
    }

}