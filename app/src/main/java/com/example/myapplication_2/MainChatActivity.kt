package com.example.myapplication_2


import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication_2.databinding.ActivityMainLayoutTestBinding
import models.UserCache
import ui.fragments.ChatFragment
import ui.objects.AppDrawer


class MainChatActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainLayoutTestBinding
    lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_layout_test)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mBinding = ActivityMainLayoutTestBinding.inflate(layoutInflater)
        setContentView(mBinding.root)



        initFields()
        initFunc()

        if (savedInstanceState == null) {
            // Создаем шторку
            mAppDrawer.create()

            // Загружаем стартовый фрагмент чата
            supportFragmentManager.beginTransaction()
                .replace(R.id.dataContainer, ChatFragment())
                .commit()
        }
//        Toast.makeText(this, "create", Toast.LENGTH_SHORT).show();


    }

    override fun onStart() {
        super.onStart()
//        Toast.makeText(this, "start.", Toast.LENGTH_SHORT).show();



    }



    private fun initFunc() {

        setSupportActionBar(mToolBar)
        supportFragmentManager.beginTransaction()
            .replace(R.id.dataContainer, ChatFragment()).commit()
        mAppDrawer.create()

    }



    private fun initFields() {
        mToolBar = mBinding.mainToolBar
        mAppDrawer = AppDrawer(this, mToolBar)


    }
}