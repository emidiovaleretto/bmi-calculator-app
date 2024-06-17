package com.comunidadedevspace.imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightUserInput = findViewById<TextInputEditText>(R.id.weight_user_input)
        val heightUserInput = findViewById<TextInputEditText>(R.id.height_user_input)

        val btnCalculate = findViewById<Button>(R.id.btn_calculate)

        btnCalculate.setOnClickListener {

            try {
                val weight: Float = weightUserInput.text.toString().toFloat()
                val height: Float = heightUserInput.text.toString().toFloat()

                val bmi = calculateBMI(weight, height)

                var intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(BMI_RESULT_KEY, bmi)
                startActivity(intent)
            } catch (e: NumberFormatException) {
                Snackbar.make(
                    weightUserInput,
                    "Required fields must be filled in",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun calculateBMI(weight: Float, height: Float): Float {
        return weight / (height * height)
    }
}