package ui.objects


import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myapplication_2.MainActivity
import com.example.myapplication_2.MainChatActivity
import com.example.myapplication_2.R
import com.mikepenz.iconics.Iconics.applicationContext
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import models.UserCache

import ui.fragments.SettingsFragmnt


class AppDrawer (private val chatActivity: AppCompatActivity,
                 private val toolbar: Toolbar){
    private lateinit var mDraver: Drawer
    private lateinit var mHeader: AccountHeader
    private lateinit var mDrawerLayout: DrawerLayout

    private lateinit var mCurrentProfile: ProfileDrawerItem



    fun create(){
        createHeader()
        createDrawer()
        mDrawerLayout = mDraver.drawerLayout
    }

    fun disableDrawer()
    {
        mDraver.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
        chatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        toolbar.setNavigationOnClickListener {
            chatActivity.supportFragmentManager.popBackStack()
        }
    }

    fun enableDrawer()
    {
        chatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mDraver.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true

        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED )
        toolbar.setNavigationOnClickListener {
            mDraver.openDrawer()
        }

    }



    private fun createDrawer() {
        DrawerBuilder()
            .withActivity(chatActivity)
            .withToolbar(toolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(mHeader)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(101)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.MyProfile))
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(102)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.secondInBar))
                    .withIcon(R.drawable.sticker),

                DividerDrawerItem(),

                PrimaryDrawerItem().withIdentifier(104)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.CreateGroup))
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(105)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.createChat))
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(106)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.contacts))
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(107)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.calls))
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(108)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.favourites))
                    //.withSelected(false)
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(109)
                    .withSelectable(false)
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.settings))
                    .withIcon(R.drawable.sticker)

            ).withOnDrawerItemClickListener(object :Drawer.OnDrawerItemClickListener{
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    //Toast.makeText(applicationContext, position.toString(), Toast.LENGTH_SHORT).show()
                    when (position) {
                        9 -> chatActivity. supportFragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.dataContainer, SettingsFragmnt()).commit()
                    }

                    return false
                }

            }).build().also { mDraver = it }

    }



    private fun createHeader() {
        val path = "C:\\Users\\user\\AndroidStudioProjects\\MyApplication_2\\app\\src\\main\\res\\drawable\\img.png"
        val fullName: String = (UserCache.currentUser?.firstName + " " + UserCache.currentUser?.lastName)
        // TODO Доделать Icon для header
        mCurrentProfile = ProfileDrawerItem()
//            .withIcon(UserCache.currentUser?.photoUrl ?: path)
            .withName(fullName)
            .withEmail(UserCache.currentUser?.phone)
            .withIdentifier(200)


        mHeader = AccountHeaderBuilder()
            .withActivity(chatActivity)
            .withHeaderBackground(R.drawable.header)
            .addProfiles(
                mCurrentProfile
            )
            .build()


    }

    private fun updateHeader(){
        val path = "C:\\Users\\user\\AndroidStudioProjects\\MyApplication_2\\app\\src\\main\\res\\drawable\\img.png"
        val fullName: String = (UserCache.currentUser?.firstName + " " + UserCache.currentUser?.lastName)

        mCurrentProfile = ProfileDrawerItem()
//            .withIcon(UserCache.currentUser?.photoUrl ?: path)
            .withName(fullName)
            .withEmail(UserCache.currentUser?.phone)
        mHeader.updateProfile(mCurrentProfile)
    }

}


