package models


import java.time.LocalDateTime

// модель для отправки сообщения,
//  user - информация о пользователе
//  text - текст сообщения
//  type - тип (фото, видео, мультимедия)
//  from - айди откуда сообщение (пока совпадает с user.id)
//  timeStamp - время отправки сообщения
data class CommonModel (
    val user: User,
    var text: String = "",
    var type: String = "",
    var from: String = "",
    var timeStamp: LocalDateTime
)
{

}

