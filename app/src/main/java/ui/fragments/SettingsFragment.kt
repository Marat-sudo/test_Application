package ui.fragments


import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
// Обязательно замените com.yourpackage на реальный ID вашего приложения!

import android.os.Bundle
import android.view.View
import com.example.myapplication_2.R

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {


    override fun onResume() {
        super.onResume()

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
                return when (menuItem.itemId) {
                    R.id.setting_menu_change_info -> true

                    R.id.setting_menu_change_photo -> true

                    R.id.setting_menu_exit -> true

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        // ^ Важно: передаем viewLifecycleOwner, чтобы меню само удалялось
    }

}