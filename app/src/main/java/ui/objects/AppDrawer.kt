package ui.objects


import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.myapplication_2.MainActivity
import com.example.myapplication_2.MainChatActivity
import com.example.myapplication_2.R
import com.example.myapplication_2.utilits.downloadAndSetImage
import com.mikepenz.iconics.Iconics.applicationContext
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader
import com.mikepenz.materialdrawer.util.DrawerImageLoader
import models.UserCache

import ui.fragments.SettingsFragmnt


class AppDrawer (private val chatActivity: AppCompatActivity,
                 private val toolbar: Toolbar){

    private val TAG = "M_DEBUG"
    private lateinit var mDraver: Drawer
    private lateinit var mHeader: AccountHeader
    private lateinit var mDrawerLayout: DrawerLayout

    private lateinit var mCurrentProfile: ProfileDrawerItem



    fun create(){
        initLoader()
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
        val p  = "app/src/main/res/drawable/m.jpg"
        val path = UserCache.currentUser?.photoUrl


        val fullName: String = (UserCache.currentUser?.firstName + " " + UserCache.currentUser?.lastName)
        /* TODO Доделать Icon для header
        * проблемы загрузки фото из базового "app/src/main/res/drawable/m.jpg", но
        * если путь через кэш то всё работает
        * */
        mCurrentProfile = ProfileDrawerItem()
            .withName(fullName)
            .withEmail(UserCache.currentUser?.phone)
            //.withIcon(path!!)
            .withIdentifier(200)


        mHeader = AccountHeaderBuilder()
            .withActivity(chatActivity)
            .withHeaderBackground(R.drawable.header)
            .addProfiles(
                mCurrentProfile
            )
            .build()


    }

    fun updateHeader(){
        val path = "C:\\Users\\user\\AndroidStudioProjects\\MyApplication_2\\app\\src\\main\\res\\drawable\\m.jpg"
        val fullName: String = (UserCache.currentUser?.firstName + " " + UserCache.currentUser?.lastName)

        mCurrentProfile
            .withName(fullName)
            .withEmail(UserCache.currentUser?.phone)
            .withIcon(UserCache.currentUser?.photoUrl ?: path)

        mHeader.updateProfile(mCurrentProfile)
    }

    private fun initLoader(){
        DrawerImageLoader.init(object: AbstractDrawerImageLoader(){
            override fun set(
                imageView: ImageView,
                uri: Uri,
                placeholder: Drawable
            ) {
                super.set(imageView, uri, placeholder)
                imageView.downloadAndSetImage(uri.toString())
            }
        })
    }
}




