package ui.fragments


import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.myapplication_2.R
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.example.myapplication_2.MainChatActivity
import com.example.myapplication_2.utilits.showToast


class ChangeInfoFragment : BaseFragment(R.layout.fragment_change_info) {
    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
        (activity as MainChatActivity).mAppDrawer.disableDrawer()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainChatActivity).mAppDrawer.enableDrawer()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainChatActivity).menuInflater.inflate(R.menu.settings_menu_confirm, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_change -> change()
        }

        return true
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

    private fun change() {
        val name = requireView().findViewById<EditText>(R.id.settings_input_name)
        val secondName = requireView().findViewById<EditText>(R.id.settings_input_surname)

        if (name.text.isEmpty()) {
            showToast("error")
        } else {
            val fullName: String = "${name.text} ${secondName.text}"
            showToast(fullName)
            parentFragmentManager.popBackStack()
        }
    }
}