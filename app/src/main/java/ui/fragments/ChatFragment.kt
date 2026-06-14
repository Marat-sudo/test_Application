package ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_2.R
import com.example.myapplication_2.utilits.APP_ACTIVITY
import com.example.myapplication_2.utilits.replaceFragment
import models.ContactsDatabase
import models.User
import models.UserCache
import ui.objects.ContactsAdapter


class ChatFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private lateinit var adapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
        loadChats()
    }

    private fun initRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.chats_recycle_view_main)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())

        // При клике на чат — открываем SingleChatFragment с этим пользователем
        adapter = ContactsAdapter(emptyList()) { clickedUser ->
            openSingleChat(clickedUser)
        }
        recyclerView?.adapter = adapter
    }

    private fun loadChats() {
        val currentUserId = UserCache.currentUser?.id ?: "1"
        // Для симуляции берем те же контакты, что и в базе (как будто у нас со всеми есть чат)
        val chatsFromDb = ContactsDatabase.getContactsForUser(currentUserId)
        adapter.updateData(chatsFromDb)
    }

    private fun openSingleChat(user: User) {
        // Открываем экран переписки, передавая туда выбранного юзера
        parentFragmentManager.beginTransaction()
            .replace(R.id.data_Container, SingleChatFragment(user))
            .addToBackStack(null) // Чтобы по кнопке "Назад" вернуться к списку чатов
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView = null
    }


}