package models

data class User (
    val id: String = "",
    var userName: String = "",
    var bio: String = "",
    var firstName: String= "",
    var lastName: String= "",
    var status: String = "",
    var phone: String = "",
    var photoUrl: String = ""
)

object UserCache {

    // Создаем переменную нашего юзера и сразу инициализируем её дефолтными значениями
    var currentUser: User? = User(
        id = "1",
        userName = "marat_dev",
        bio = "тест тест тест тест тест тест тест тест тест тест тест тест тест тест тест",
        firstName = "m",
        lastName = "n",
        phone = "+7 000 000 00 00",
        photoUrl = "C:\\Users\\user\\AndroidStudioProjects\\MyApplication_2\\app\\src\\main\\res\\drawable\\img.png"
    )
}