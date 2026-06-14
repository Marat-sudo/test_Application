package ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication_2.R
import com.example.myapplication_2.utilits.showToast
import models.UserCache

class ChangePhoneFragment : BaseChangeFragment(R.layout.fragment_change_phone) {

    override fun change() {
        UserCache.currentUser
        val phone = requireView().findViewById<EditText>(R.id.setting_replace_phone)
        if (phone.text.isEmpty()) {
            parentFragmentManager.popBackStack()
        }
        else {
            val log = (phone.text.toString()).replace("\\s".toRegex(), "")
            val regex = """\+\d{11}""".toRegex()
            if (log.matches(regex))
            {
                val userName: String = "${phone.text}"
                showToast(userName)
                UserCache.currentUser?.userName = userName
                parentFragmentManager.popBackStack()
            }
            else
            {
                showToast("неверный ввод телефона")
            }

        }
    }

}