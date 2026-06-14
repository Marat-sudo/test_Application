package ui.fragments


import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication_2.R
import com.example.myapplication_2.utilits.APP_ACTIVITY
import de.hdodenhof.circleimageview.CircleImageView
import models.CommonModel
import models.User

class SingleChatFragment(private val contact: User) : BaseFragment(R.layout.fragment_single_chat) {
    private val infoPanel = APP_ACTIVITY.mToolBar.findViewById<View>(R.id.toolbar_info)

    override fun onResume() {
        super.onResume()

        infoPanel.visibility = View.VISIBLE

        setupChatToolbar()
    }

    private fun setupChatToolbar() {
        // 1. Находим саму инфо-панель внутри тулбара и делаем её ВИДИМОЙ
        val toolbarInfo = APP_ACTIVITY.findViewById<View>(R.id.toolbar_info)
        toolbarInfo.visibility = View.VISIBLE

        // 2. Находим элементы разметки внутри этой инфо-панели
        val contactName = APP_ACTIVITY.findViewById<TextView>(R.id.tool_bar_info_fullName)
        val contactStatus = APP_ACTIVITY.findViewById<TextView>(R.id.tool_bar_info_status)
        val contactPhoto = APP_ACTIVITY.findViewById<CircleImageView>(R.id.tool_bar_info_image)

        // 3. Заполняем имя (Имя + Фамилия, либо userName, если имя пустое)
        val fullName = "${contact.firstName} ${contact.lastName}".trim()
        contactName.text = if (fullName.isEmpty()) contact.userName else fullName

        // 4. Заполняем статус
        contactStatus.text = contact.status.ifEmpty { "не в сети" }

        // 5. Загружаем аватарку контакта с помощью Glide
        if (contact.photoUrl.isNotEmpty()) {
            Glide.with(this)
                .load(contact.photoUrl)
                .placeholder(R.drawable.img) // дефолтная заглушка
                .error(R.drawable.img)
                .into(contactPhoto)
        } else {
            contactPhoto.setImageResource(R.drawable.img)
        }
    }

    override fun onPause() {
        super.onPause()
        infoPanel.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        infoPanel.visibility = View.GONE
    }
}