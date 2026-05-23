package ui.fragments


import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.myapplication_2.R
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.example.myapplication_2.MainChatActivity
import com.example.myapplication_2.utilits.showToast


class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {
    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 1. Получаем MenuHost (обычно это наша Activity)
        val menuHost: MenuHost = requireActivity()

        // 2. Добавляем MenuProvider
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Раздуваем меню
                menuInflater.inflate(R.menu.settings_menu_confirm, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Обрабатываем нажатия
                when (menuItem.itemId) {
                    R.id.settings_confirm_change -> change()

                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        // 3. Указываем владельца жизненного цикла и состояние
    }




    override fun change() {
        val name = requireView().findViewById<EditText>(R.id.settings_input_name)
        val secondName = requireView().findViewById<EditText>(R.id.settings_input_surname)

        if (name.text.isEmpty()) {
            parentFragmentManager.popBackStack()
        }
        else {
            val fullName: String = "${name.text} ${secondName.text}"
            showToast(fullName)
            parentFragmentManager.popBackStack()
        }
    }
}