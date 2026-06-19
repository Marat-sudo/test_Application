package models

object ContactsDatabase {

    // Ключ: ID пользователя. Значение: Список ID-шников его друзей
    private val contactsRelationTable = mutableMapOf<String, MutableList<String>>()

    init {
        contactsRelationTable["1"] = mutableListOf("2", "3")
        contactsRelationTable["2"] = mutableListOf("1", "3")
        contactsRelationTable["3"] = mutableListOf("2")
    }

    /**
     Получить полноценные профили контактов для конкретного пользователя
     */
    fun getContactsForUser(userId: String): List<User> {
        val contactIds = contactsRelationTable[userId] ?: return emptyList()

        return contactIds.mapNotNull { id -> UserDatabase.getUserById(id) }
    }

    /**
     Добавить в контакты по ID
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