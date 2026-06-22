package models

import com.example.myapplication_2.R
import com.example.myapplication_2.utilits.AppStates

data class User (
    val id: String = "",
    var userName: String = "",
    var bio: String = "",
    var firstName: String= "",
    var lastName: String= "",
    var status: String = "",
    var phone: String = "",
    var photoUrl: String = "",
    var state: String ="",
    var userBg: String = ""
)

object UserCache {

    // Создаем переменную нашего юзера и сразу инициализируем её дефолтными значениями
    var currentUser: User? = null
}