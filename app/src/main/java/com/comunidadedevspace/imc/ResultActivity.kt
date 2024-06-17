package com.comunidadedevspace.imc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

const val BMI_RESULT_KEY = "ResultActivity.BMI_KEY"

class ResultActivity : AppCompatActivity() {

    private lateinit var bmiDescriptions: Map<String, String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        loadBMIDescriptions()

        val resultIntent = intent.getFloatExtra(BMI_RESULT_KEY, 0f)
        val bmiTextviewResult = findViewById<TextView>(R.id.bmi_result)
        val bmiTextViewWeightStatus = findViewById<TextView>(R.id.textView_BMI_weight_status)

        val resultBMIWeightStatus = findViewById<TextView>(R.id.result_BMI_weight_status)
        val bmiTextViewDescription = findViewById<TextView>(R.id.result_description)

        val bmiSeekBar: SeekBar = findViewById(R.id.bmiSeekBar)
        val (progress, weightStatus) = getBMIData(resultIntent)

        val btnStartOver = findViewById<Button>(R.id.btn_start_over)

        btnStartOver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        bmiSeekBar.progress = progress

        bmiTextviewResult.text = resultIntent.toString()

        bmiTextViewWeightStatus.text = weightStatus
        resultBMIWeightStatus.text = weightStatus
        bmiTextViewDescription.text = bmiDescriptions[weightStatus]
    }

    private fun getBMIData(bmi: Float): Pair<Int, String> {
        return when {
            bmi < 18.5f -> Pair(25, "Underweight")
            bmi < 24.9f -> Pair(50, "Normal weight")
            bmi < 29.9f -> Pair(75, "Overweight")
            else -> Pair(100, "Obesity")
        }
    }

    private fun loadBMIDescriptions() {
        val inputStream = assets.open("bmi_descriptions.json")
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        bufferedReader.useLines { lines -> lines.forEach { stringBuilder.append(it) } }
        val jsonString = stringBuilder.toString()
        val jsonObject = JSONObject(jsonString)
        bmiDescriptions =
            jsonObject.keys().asSequence().associateWith { jsonObject.getString(it) }
    }
}