package com.example.myapplication_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegActivityCodePhone : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reg_code_phone)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backButton: Button = findViewById(R.id.back_To_Phone_Register)

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            // ответ от бэка
            val tinCode = true
            val code: EditText = findViewById(R.id.userCode)
            val log = code.text.toString().trim()

            if (log != "" && tinCode){
                val intent = Intent(this, Register_user_last::class.java)
                startActivity(intent)
            }

            else
                Toast.makeText(this, "проблема с кодом", Toast.LENGTH_LONG).show()
        }
    }

}