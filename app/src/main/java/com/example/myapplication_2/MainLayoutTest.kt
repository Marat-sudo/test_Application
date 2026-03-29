package com.example.myapplication_2

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.View
import android.widget.Toast
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
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import ui.ChatFragment

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
        mDraver =  DrawerBuilder()
            .withActivity(this)
            .withToolbar(mToolBar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(mHeader)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(100)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(getString(R.string.MyProfile))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(101)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(getString(R.string.secondInBar))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker),

                DividerDrawerItem(),

                PrimaryDrawerItem().withIdentifier(102)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(getString(R.string.CreateGroup))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(103)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(getString(R.string.createChat))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(104)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(getString(R.string.contacts))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(105)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(getString(R.string.calls))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(106)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(getString(R.string.favourites))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(107)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(getString(R.string.settings))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker)

            ).withOnDrawerItemClickListener(object :Drawer.OnDrawerItemClickListener{
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    Toast.makeText(applicationContext, position.toString(), Toast.LENGTH_SHORT).show()
                    return false
                }

            }).build()

    }

    private fun initFunc() {
        setSupportActionBar(mToolBar)
        supportFragmentManager.beginTransaction()
            .replace(R.id.dataContainer, ChatFragment()).commit()
        createHeader()
        createDrawer()
    }

    private fun createHeader() {
        mHeader = AccountHeaderBuilder()
            .withActivity(this)
            .withHeaderBackground(R.drawable.header)
            .addProfiles(
                ProfileDrawerItem().withName("всё будет")
                    .withEmail("+79021202616")
            )
            .build()


    }

    private fun initFields() {
        mToolBar = mBinding.mainToolBar

    }
}