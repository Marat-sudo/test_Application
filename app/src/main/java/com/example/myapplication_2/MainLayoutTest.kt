package com.example.myapplication_2

import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication_2.databinding.ActivityMainLayoutTestBinding
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem

class MainLayoutTest : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainLayoutTestBinding
    private lateinit var mDraver: Drawer
    private lateinit var mHeader: AccountHeader
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
        createDrawer()
    }

    private fun createDrawer() {
        mDraver = DrawerBuilder()
            .withActivity(this)
            .withToolbar(mToolBar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(mHeader)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("штука тестовая")
                    .withSelected(false)
            )
            .build()
    }

    private fun initFunc() {
        setSupportActionBar(mToolBar)
        createHeader()
    }

    private fun createHeader() {
        mHeader = AccountHeaderBuilder()
            .withActivity(this)
            .withHeaderBackground(R.drawable.header)
            .addProfiles(
                ProfileDrawerItem().withName("where бэк")
                    .withEmail("+79021202616")
            )
            .build()


    }

    private fun initFields() {
        mToolBar = mBinding.mainToolBar

    }
}