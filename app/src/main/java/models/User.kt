package models

data class User (
    val id: String = "",
    var userName: String = "",
    var bio: String = "",
    var fullName: String= "",
    var status: String = "",
    var phone: String = "",
    var photoUrl: String = ""
)