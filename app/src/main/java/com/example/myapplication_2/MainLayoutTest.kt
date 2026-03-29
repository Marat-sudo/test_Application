package com.example.myapplication_2


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication_2.databinding.ActivityMainLayoutTestBinding
import ui.fragments.ChatFragment
import ui.objects.AppDrawer


class MainLayoutTest : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainLayoutTestBinding
    private lateinit var appDrawer: AppDrawer
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


    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()

    }



    private fun initFunc() {

        setSupportActionBar(mToolBar)
        supportFragmentManager.beginTransaction()
            .replace(R.id.dataContainer, ChatFragment()).commit()
        appDrawer.create()

    }



    private fun initFields() {
        mToolBar = mBinding.mainToolBar
        appDrawer = AppDrawer(this, mToolBar)


    }
}