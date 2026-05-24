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


class ChangeInfoFragment : BaseChangeFragment(R.layout.fragment_change_info) {
    override fun onResume() {
        super.onResume()
    }


    override fun change() {
        val name = requireView().findViewById<EditText>(R.id.settings_input_name)
        val secondName = requireView().findViewById<EditText>(R.id.settings_input_surname)

        if (name.text.isEmpty()) {
            parentFragmentManager.popBackStack()

        } else {
            val fullName: String = "${name.text} ${secondName.text}"
            showToast(fullName)
            parentFragmentManager.popBackStack()
        }
    }
}