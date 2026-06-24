package ui.fragments.single_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_2.R
import models.CommonModel
import models.User
import models.UserCache
import java.time.format.DateTimeFormatter

class SingleChatAdapter : RecyclerView.Adapter<SingleChatAdapter.SingleChatHolder>() {

    private var listMessageCache = mutableListOf<CommonModel>()

    // 1. Создаем форматтер, который оставляет только Часы и Минуты


    override fun onCreateViewHolder(
         parent: ViewGroup,
        viewType: Int
    ): SingleChatHolder {
        // штука для создания холдера во фрагменте
        val view = LayoutInflater.
            from(parent.context).
                inflate(R.layout.message_item, parent, false)
        return SingleChatHolder(view)
    }

    override fun onBindViewHolder(
        holder: SingleChatHolder,
        position: Int
    ) {
        // формат для времени в чате
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        // переводим в нормальный формат сообщение
        val formattedTime = listMessageCache[position].timeStamp.format(timeFormatter)

        // к холдеру в зависимости от сообщения изменяем вид recycle view
        if (listMessageCache[position].from == UserCache.currentUser?.id){
            holder.blockUserMessage.visibility = View.VISIBLE
            holder.blockReceivedMessage.visibility = View.GONE
            holder.chatUserMessage.text = listMessageCache[position].text
            holder.chatUserMessageTime.text = formattedTime
        }
        else {
            holder.blockUserMessage.visibility = View.GONE
            holder.blockReceivedMessage.visibility = View.VISIBLE
            holder.chatReceivedMessage.text = listMessageCache[position].text
            holder.chatReceivedMessageTime.text = formattedTime
        }

    }

    override fun getItemCount(): Int {
        return listMessageCache.size
    }

    class SingleChatHolder(view: View) : RecyclerView.ViewHolder(view){
        val blockUserMessage: ConstraintLayout = view.findViewById(R.id.bloc_user_message)
        val chatUserMessage: TextView = view.findViewById(R.id.chat_user_message)
        val chatUserMessageTime: TextView = view.findViewById(R.id.chat_user_message_time)

        val blockReceivedMessage: ConstraintLayout = view.findViewById(R.id.bloc_received_message)
        val chatReceivedMessage: TextView = view.findViewById(R.id.chat_received_message)
        val chatReceivedMessageTime: TextView = view.findViewById(R.id.chat_received_message_time)

    }

    fun setList(list: List<CommonModel>){
        listMessageCache.clear()
        listMessageCache.addAll(list)
    }

    fun addMessage(mes: CommonModel){
        listMessageCache.add(mes)
        notifyItemInserted(listMessageCache.size - 1)
    }
}