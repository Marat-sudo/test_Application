package ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_2.R
import com.example.myapplication_2.utilits.APP_ACTIVITY
import models.ContactsDatabase
import models.User
import models.UserCache
import ui.objects.ContactsAdapter

class ContactsFragment : BaseFragment(R.layout.fragment_contacts) {
    private var mRecyclerView: RecyclerView? = null
    private lateinit var adapter: ContactsAdapter


    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Друзья"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    // 2. Вся работа с View (поиск по ID, настройка) делается ТУТ
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(view)
        loadContacts()

    }

    private fun initRecyclerView(view: View) {
        // Ищем RecyclerView внутри переданного view фрагмента
        mRecyclerView = view.findViewById(R.id.contacts_recycle_view)

        // Вместо "this" передаем "requireContext()" (гарантирует получение контекста)
        mRecyclerView?.layoutManager = LinearLayoutManager(requireContext())

        // Инициализируем адаптер
        adapter = ContactsAdapter(emptyList())
        mRecyclerView?.adapter = adapter
    }

    private fun loadContacts() {
        // Получаем ID текущего пользователя из вашего кэша
        val currentUserId = UserCache.currentUser?.id ?: "1"

        // Тянем данные из симулированной БД
        val contactsFromDb = ContactsDatabase.getContactsForUser(currentUserId)

        // Обновляем адаптер
        adapter.updateData(contactsFromDb)
    }

    // 3. ОБЯЗАТЕЛЬНО обнуляем ссылку на RecyclerView для предотвращения утечек памяти
    override fun onDestroyView() {
        super.onDestroyView()
        mRecyclerView = null
    }
}



