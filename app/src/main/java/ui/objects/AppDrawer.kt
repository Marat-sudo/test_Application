package ui.objects


import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
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
import ui.fragments.SettingsFragment


class AppDrawer (private val chatActivity: AppCompatActivity,
                 private val toolbar: Toolbar){
    private lateinit var mDraver: Drawer
    private lateinit var mHeader: AccountHeader

    fun create(){
        createHeader()
        createDrawer()
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
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.MyProfile))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(102)
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.secondInBar))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker),

                DividerDrawerItem(),

                PrimaryDrawerItem().withIdentifier(104)
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.CreateGroup))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(105)
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.createChat))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(106)
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.contacts))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(107)
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.calls))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(108)
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.favourites))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker),

                PrimaryDrawerItem().withIdentifier(109)
                    .withIconTintingEnabled(true)
                    .withName(chatActivity.getString(R.string.settings))
                    .withSelected(false)
                    .withIcon(R.drawable.sticker)

            ).withOnDrawerItemClickListener(object :Drawer.OnDrawerItemClickListener{
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    Toast.makeText(applicationContext, position.toString(), Toast.LENGTH_SHORT).show()
                    when (position) {
                        9 -> chatActivity. supportFragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.dataContainer, SettingsFragment()).commit()
                    }

                    return false
                }

            }).build().also { mDraver = it }

    }



    private fun createHeader() {
        mHeader = AccountHeaderBuilder()
            .withActivity(chatActivity)
            .withHeaderBackground(R.drawable.header)
            .addProfiles(
                ProfileDrawerItem().withName("всё будет")
                    .withEmail("+79021202616")
            )
            .build()


    }
}