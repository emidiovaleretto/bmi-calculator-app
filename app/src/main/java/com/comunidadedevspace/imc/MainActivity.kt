package com.comunidadedevspace.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightUserInput = findViewById<TextInputEditText>(R.id.weight_user_input)
        val heightUserInput = findViewById<TextInputEditText>(R.id.height_user_input)

        val btnCalculate = findViewById<Button>(R.id.btn_calculate)

        btnCalculate.setOnClickListener {
            val weight: Float = weightUserInput.text.toString().toFloat()
            val height: Float  = heightUserInput.text.toString().toFloat()

            val bmi: Float = weight / (height * height)

            println("The BMI is: ${bmi}")
        }
    }
}