package models

import java.time.LocalDateTime

object MessagesDatabase {

    // Ключ: ID чата (например, "1_2"). Значение: Список сообщений в этом чате
    private val messagesTable = mutableMapOf<String, MutableList<CommonModel>>()


    /**
    Генерация уникального ключа чата для двух пользователей.
    Сортировка ID важна, чтобы для ("1", "2") и ("2", "1") ключ всегда был одинаковым: "1_2"
     */
    private fun getChatKey(userId1: String, userId2: String): String {
        return if (userId1 < userId2) "${userId1}_${userId2}" else "${userId2}_${userId1}"
    }

    /**
    Получить историю сообщений между двумя пользователями
     */
    fun getMessagesForChat(currentUserId: String, contactId: String): MutableList<CommonModel> {
        val chatKey = getChatKey(currentUserId, contactId)
        // Возвращаем копию списка, чтобы никто случайно не испортил внутреннюю БД
        return messagesTable[chatKey] ?: mutableListOf()
    }

    /**
    Сохранить новое сообщение в базу данных
     */
    fun saveMessage(currentUserId: String, contactId: String, message: CommonModel) {
        val chatKey = getChatKey(currentUserId, contactId)

        if (messagesTable.containsKey(chatKey)) {
            messagesTable[chatKey]?.add(message)
        } else {
            messagesTable[chatKey] = mutableListOf(message)
        }
    }
}