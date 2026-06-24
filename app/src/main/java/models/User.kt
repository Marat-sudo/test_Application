package models

import com.example.myapplication_2.R
import com.example.myapplication_2.utilits.AppStates

// общая модель
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

    // TODO потом перенести куда-то
    // здесь храниться модель конкретного юзера
    var currentUser: User? = null
}