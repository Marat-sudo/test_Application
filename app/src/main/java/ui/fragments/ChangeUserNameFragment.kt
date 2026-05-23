package ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.example.myapplication_2.R
import com.example.myapplication_2.utilits.showToast

class ChangeUserNameFragment : BaseChangeFragment(R.layout.fragment_change_username) {

    override fun onResume() {
        super.onResume()
    }

    override fun change() {
        val name = requireView().findViewById<EditText>(R.id.setting_input_username)
        if (name.text.isEmpty()) {
            parentFragmentManager.popBackStack()
        }
        else {
            val fullName: String = "${name.text}"
            showToast(fullName)
            parentFragmentManager.popBackStack()
        }
    }





}