package ui.fragments


import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle

import android.os.Bundle
import android.view.View
import com.example.myapplication_2.MainChatActivity
import com.example.myapplication_2.R
import com.example.myapplication_2.RegOrLog
import com.example.myapplication_2.Register_user_last
import com.example.myapplication_2.utilits.replaceActivity
import com.example.myapplication_2.utilits.replaceFragment

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {


    override fun onResume() {
        super.onResume()

    }

    private fun initFields()
    {

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()

        // 2. Добавляем провайдер меню
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Раздуваем ваше меню здесь
                menuInflater.inflate(R.menu.settings_action_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Обрабатываем нажатия
                when (menuItem.itemId) {
                    R.id.setting_menu_change_info -> replaceFragment(ChangeNameFragment())

                    //R.id.setting_menu_change_photo -> pass

                    R.id.setting_menu_exit -> (activity as MainChatActivity).replaceActivity(RegOrLog())


                }
                return true

            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        // ^ Важно: передаем viewLifecycleOwner, чтобы меню само удалялось
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting_menu_exit -> {
                (activity as MainChatActivity).replaceActivity(RegOrLog())
            }
            R.id.setting_menu_change_info -> replaceFragment(ChangeNameFragment())

        }

        return true
    }
}

