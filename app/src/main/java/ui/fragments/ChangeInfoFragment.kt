package ui.fragments



import android.widget.EditText
import com.example.myapplication_2.R
import models.UserCache


class ChangeInfoFragment : BaseChangeFragment(R.layout.fragment_change_info) {
    override fun onResume() {
        super.onResume()
    }


    override fun change() {
        val name: EditText = requireView().findViewById(R.id.settings_input_name)
        val secondName: EditText = requireView().findViewById(R.id.settings_input_surname)
        val bio: EditText = requireView().findViewById(R.id.settings_input_about)


        if (name.text.isNotEmpty()) {
            UserCache.currentUser?.firstName = name.text.toString()

        }
        if (secondName.text.isNotEmpty()) {
            UserCache.currentUser?.lastName = secondName.text.toString()
        }

        if (bio.text.isNotEmpty()) {
            UserCache.currentUser?.bio = bio.text.toString()
        }

        parentFragmentManager.popBackStack()

    }
}