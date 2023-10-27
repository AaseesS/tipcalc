package edu.uw.ischool.asingh28.tipcalc

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private lateinit var amountEditText: EditText
    private lateinit var tipButton: Button
    private val decimalFormat = DecimalFormat("$#,##0.00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountEditText = findViewById(R.id.amountEditText)
        tipButton = findViewById(R.id.tipButton)

        tipButton.isEnabled = false

        amountEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = s.toString()
                if (text.isNotBlank()) {
                    val cleanText = text.replace("$", "").replace(",", "")
                    try {
                        val amount = cleanText.toDouble()
                        tipButton.isEnabled = true
                    } catch (e: NumberFormatException) {
                        tipButton.isEnabled = false
                    }
                } else {
                    tipButton.isEnabled = false
                }
            }
        })

        tipButton.setOnClickListener {
            val amountText = amountEditText.text.toString().replace("$", "").replace(",", "")
            val amount = amountText.toDouble()
            val tipAmount = amount * 0.15
            val formattedTip = decimalFormat.format(tipAmount)
            val toastMessage = "Tip: $formattedTip"
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }
}

