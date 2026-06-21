package models

import com.example.myapplication_2.utilits.AppStates
import models.UserCache.currentUser
object UserDatabase {

    // Ключ — String (id пользователя), Значение — сам User
    private val usersTable = mutableMapOf<String, User>()

    init {
        val user1 = User(
            id = "1",
            userName = "marat_dev",
            bio = "тест тест тест тест тест тест тест тест тест тест тест тест тест тест тест",
            firstName = "m",
            lastName = "n",
            phone = "+79000000000"
        )
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

        usersTable[user1.id] = user1
        usersTable[user2.id] = user2
        usersTable[user3.id] = user3
    }

    /**
    Сохранить или обновить пользователя (Аналог INSERT OR REPLACE)
     */
    fun saveUser(user: User?) {
        if (user == null) return
        usersTable[user.id] = user
    }

    /**
    Найти пользователя по его ID
     */
    fun getUserById(id: String): User? {
        return usersTable[id]?.copy()
    }

    /**
    Поиск пользователя по номеру телефона
     */
    fun getUserByPhone(phone: String): User? {
        println(usersTable)
        return usersTable.values.find { it.phone == phone }?.copy()
    }

    /**
    Получить список всех пользователей системы
     */
    fun getAllUsers(): List<User> {
        return usersTable.values.map { it.copy() }
    }
}