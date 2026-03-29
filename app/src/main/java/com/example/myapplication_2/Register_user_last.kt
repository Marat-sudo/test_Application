package com.example.myapplication_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.widget.ImageView
import androidx.core.view.WindowInsetsCompat
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import coil.load

class Register_user_last : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_user_last)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageView: ImageView = findViewById(R.id.img_test)
        val nextB: Button = findViewById(R.id.next_button)
        // создается регистер, контакт - PickVisualMedia
        //val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia())
        //{ uri ->
        //    if (uri != null) {              // uri - адрес в памяти телефона
        //        imageView.setImageURI(uri)  // заменяет картинка в imageView
        //    }
        //}


        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia())
        { uri ->
            if (uri != null) {
                imageView.load(uri) {
                    // Или просто ограничиваем по контейнеру
                    crossfade(true)
                }
            }
        }
        // нажатие на кнопку
        imageView.setOnClickListener {
            // запрос на вызов Photo Picker (выборщика медиафайлов)
            // ImageOnly - фильтр только на фото
            // launch отпрвяелт пользоваотеля в галлерею
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }


        nextB.setOnClickListener {
            val intent = Intent(this, MainChatActivity::class.java)
            startActivity(intent)
        }


    }
}