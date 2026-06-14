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
import com.example.myapplication_2.utilits.replaceActivity

class LoginUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backButton: Button = findViewById(R.id.back_To_Phone)

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val nextButton: Button = findViewById(R.id.login_next_button)
        nextButton.setOnClickListener {
            // ответ от бэка
            val timCode = true
            val code: EditText = findViewById(R.id.login_userCode)
            val log = code.text.toString().trim()

            if (log != "" && timCode){
                replaceActivity(MainChatActivity())
            }

            else
                Toast.makeText(this, getString(R.string.codeError), Toast.LENGTH_LONG).show()
        }
    }
}