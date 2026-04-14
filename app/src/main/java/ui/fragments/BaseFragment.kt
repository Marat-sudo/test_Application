package ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication_2.MainChatActivity

open class BaseFragment(val layout: Int): Fragment(layout) {


    override fun onStart() {
        super.onStart()
        (activity as MainChatActivity).mAppDrawer.disableDrawer()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainChatActivity).mAppDrawer.enableDrawer()
    }
}