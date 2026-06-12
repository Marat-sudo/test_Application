package models

object ContactsDatabase {

    // Ключ: ID пользователя. Значение: Список ID-шников его друзей (контактов)
    // Например: "1" -> ["2", "3"] (У Марата в контактах Иван и Елена)
    private val contactsRelationTable = mutableMapOf<String, MutableList<String>>()

    init {
        // Заполняем тестовые связи: у юзера с ID "1" в контактах юзеры с ID "2" и "3"
        contactsRelationTable["1"] = mutableListOf("2", "3")
    }

    /**
     * Получить полноценные профили контактов для конкретного пользователя
     */
    fun getContactsForUser(userId: String): List<User> {
        val contactIds = contactsRelationTable[userId] ?: return emptyList()

        // Магия Kotlin: берем ID-шники, ищем каждого пользователя в глобальной UserDatabase
        // и собираем их в итоговый список. Плюс фильтруем null (на случай, если юзера удалили из БД)
        return contactIds.mapNotNull { id -> UserDatabase.getUserById(id) }
    }

    /**
     * Добавить в контакты по ID
     */
    fun addContactToUser(userId: String, contactId: String) {
        if (contactsRelationTable.containsKey(userId)) {
            if (contactsRelationTable[userId]?.contains(contactId) == false) {
                contactsRelationTable[userId]?.add(contactId)
            }
        } else {
            contactsRelationTable[userId] = mutableListOf(contactId)
        }
    }
}