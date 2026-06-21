package ui.fragments.single_chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication_2.R
import com.example.myapplication_2.utilits.APP_ACTIVITY
import de.hdodenhof.circleimageview.CircleImageView
import models.CommonModel
import models.MessagesDatabase
import models.User
import models.UserCache
import ui.fragments.BaseFragment
import java.time.LocalDateTime

class SingleChatFragment(private val contact: User) : BaseFragment(R.layout.fragment_single_chat) {
    private val infoPanel = APP_ACTIVITY.mToolBar.findViewById<View>(R.id.toolbar_info)
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatInputMessage: EditText
    private lateinit var chatSendBtn: ImageView

    private lateinit var chatAdapter: SingleChatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Инициализируем View элементы из разметки фрагмента
        chatRecyclerView = view.findViewById(R.id.chat_recycler_view)
        chatInputMessage = view.findViewById(R.id.chat_input_message)
        chatSendBtn = view.findViewById(R.id.chat_set_img)

        // 2. Инициализируем и настраиваем ваш адаптер
        // Передаем currentUserId, чтобы адаптер понимал, какие сообщения "мои", а какие "входящие"
        chatAdapter = SingleChatAdapter()

        chatRecyclerView.layoutManager = LinearLayoutManager(requireContext()).apply {
            stackFromEnd = true // Список всегда прокручен вниз к свежим сообщениям
        }
        chatRecyclerView.adapter = chatAdapter

        // 3. Вешаем слушатель на кнопку отправки (человечка)
        chatSendBtn.setOnClickListener {
            sendMessage()
        }

        // 1. ЗАГРУЗКА ИЗ БД: Берем сообщения между мной и открытым контактом
        val history = MessagesDatabase.getMessagesForChat(UserCache.currentUser?.id!!, contact.id)
        chatAdapter.setList(history) // Заполняем адаптер данными
        chatRecyclerView.scrollToPosition(chatAdapter.itemCount - 1) // Скроллим вниз

        chatSendBtn.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val messageText = chatInputMessage.text.toString().trim()

        if (messageText.isEmpty()) return
        if (UserCache.currentUser == null) return

        val userId = UserCache.currentUser?.id ?: "-1"
        // Создаем модель сообщения
        val newMessage = CommonModel(
            user = UserCache.currentUser!!,
            text = messageText,
            from = userId,
            timeStamp = LocalDateTime.now() // Текущее время отправки
        )
        MessagesDatabase.saveMessage(UserCache.currentUser!!.id, contact.id, newMessage)
        chatAdapter.addMessage(newMessage)

        // Очищаем поле ввода текста
        chatInputMessage.text.clear()

        // Плавно скроллим список к самому последнему добавленному элементу
        chatRecyclerView.smoothScrollToPosition(chatAdapter.itemCount - 1)


    }

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