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
import models.UserCache
import models.UserDatabase


class RegOrLog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reg_or_log)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userPhone: EditText = findViewById(R.id.userPhone)
        val buttonReg: Button = findViewById(R.id.buttonRegister)

        buttonReg.setOnClickListener {
            val timStatus = true
            val log = (userPhone.text.toString()).replace("\\s".toRegex(), "")
            val regex = """\+\d{11}""".toRegex()
            println(log)
            if (log != "" && timStatus && log.matches(regex)){
                val defaultUser = UserDatabase.getUserByPhone(log)
                println(defaultUser)
                if (defaultUser != null){
                    UserCache.currentUser = defaultUser
                    replaceActivity(LoginUserActivity())
                }
                else {
                    UserCache.currentUser?.phone = log
                    val intent = Intent(this, RegActivityCodePhone::class.java)
                    startActivity(intent)
                }
            }

            else
                Toast.makeText(this, getString(R.string.phoneError) + log, Toast.LENGTH_LONG).show()
        }
    }
}