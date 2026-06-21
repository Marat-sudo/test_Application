package ui.objects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide // Если используете Glide для загрузки фото
import de.hdodenhof.circleimageview.CircleImageView
import com.example.myapplication_2.R // Укажите ваш пакет
import com.example.myapplication_2.utilits.AppStates
import models.CommonModel
import models.User

class ContactsAdapter(
    private var contactsList: List<User>,
    private val onItemClick: (User) -> Unit) :
    RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    // ViewHolder находит элементы по ID строго из вашего XML
    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactPhoto: CircleImageView = itemView.findViewById(R.id.contact_photo)
        val contactFullName: TextView = itemView.findViewById(R.id.contact_fullName)
        val contactStatus: TextView = itemView.findViewById(R.id.contact_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item, parent, false) // Убедитесь, что имя файла совпадает
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactsList[position]


        // 1. Склеиваем имя и фамилию (убираем лишние пробелы через trim())
        val fullName = "${contact.firstName} ${contact.lastName}".trim()
        holder.contactFullName.text = if (fullName.isEmpty()) contact.userName else fullName
        holder.contactStatus.text = contact.status.ifEmpty { "не в сети" }

        // 2. Устанавливаем статус (если пустой — пишем дефолтный, например "Offline")
        holder.contactStatus.text = contact.status.ifEmpty { AppStates.OFFLINE.state }
        // 2. Обрабатываем нажатие на ВСЮ область элемента списка
        holder.itemView.setOnClickListener {
            onItemClick(contact) // Передаем данные нажатого контакта вверх
        }

        // 3. Загружаем фото профиля
        if (contact.photoUrl.isNotEmpty()) {
            // Если photoUrl — это ссылка или путь к файлу (используем Glide):
            Glide.with(holder.itemView.context)
                .load(contact.photoUrl)
                .placeholder(R.drawable.img) // Ваша дефолтная картинка из XML при загрузке
                .error(R.drawable.img)       // Она же в случае ошибки
                .into(holder.contactPhoto)
        } else {
            // Если фото нет, ставим стандартную заглушку из вашего XML
            holder.contactPhoto.setImageResource(R.drawable.img)
        }
    }

    override fun getItemCount(): Int = contactsList.size

    // Метод для обновления данных в RecyclerView
    fun updateData(newContacts: List<User>) {
        this.contactsList = newContacts
        notifyDataSetChanged()
    }
}