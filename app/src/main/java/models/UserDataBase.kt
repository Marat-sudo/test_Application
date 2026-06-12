package models

import models.UserCache.currentUser
object UserDatabase {

    // Симуляция таблицы "Users". Ключ — String (id пользователя), Значение — сам User
    private val usersTable = mutableMapOf<String, User>()

    init {
        // Наполняем нашу базу дефолтными пользователями при запуске приложения

        val user2 = User(
            id = "2",
            userName = "maxLover",
            firstName = "Ярослав",
            lastName = "Безруков",
            phone = "+79111111111"
        )
        val user3 = User(
            id = "3",
            userName = "tim",
            firstName = "Тима",
            lastName = "тож Безруков",
            phone = "+79222222222"
        )

        usersTable[currentUser?.id ?: "-1"] = currentUser?: user2
        usersTable[user2.id] = user2
        usersTable[user3.id] = user3
    }

    /**
     * Сохранить или обновить пользователя (Аналог INSERT OR REPLACE)
     */
    fun saveUser(user: User) {
        usersTable[user.id] = user
    }

    /**
     * Найти пользователя по его ID (Аналог SELECT * FROM users WHERE id = :id)
     */
    fun getUserById(id: String): User? {
        // Возвращаем копию объекта, чтобы случайно не испортить данные в БД напрямую
        return usersTable[id]?.copy()
    }

    /**
     * Поиск пользователя по номеру телефона (например, для авторизации или проверки контактов)
     */
    fun getUserByPhone(phone: String): User? {
        return usersTable.values.find { it.phone == phone }?.copy()
    }

    /**
     * Получить список вообще всех пользователей системы
     */
    fun getAllUsers(): List<User> {
        return usersTable.values.map { it.copy() }
    }
}