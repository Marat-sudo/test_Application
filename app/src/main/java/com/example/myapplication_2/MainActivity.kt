package com.example.myapplication_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication_2.utilits.AppStates
import com.example.myapplication_2.utilits.replaceActivity
import models.User
import models.UserCache.currentUser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val br: Button = findViewById(R.id.button4)
        val bl: Button = findViewById(R.id.button3)


        br.setOnClickListener {
            val intent = Intent(this, RegOrLog::class.java)
            startActivity(intent)
        }

        bl.setOnClickListener {
            currentUser = User(
                id = "1",
                userName = "marat_dev",
                bio = "тест тест тест тест тест тест тест тест тест тест тест тест тест тест тест",
                firstName = "m",
                lastName = "n",
                phone = "+79000000000",
                state = AppStates.OFFLINE.state
            )
            replaceActivity(MainChatActivity())
        }

    }
}