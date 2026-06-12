package ui.fragments

import androidx.fragment.app.Fragment
import com.example.myapplication_2.utilits.APP_ACTIVITY

class SingleChatFragment : Fragment() {
    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = ""
    }
}