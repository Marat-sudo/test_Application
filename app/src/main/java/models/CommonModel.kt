package models


import java.time.LocalDateTime

data class CommonModel (
    val user: User,
    var text: String = "",
    var type: String = "",
    var from: String = "",
    var timeStamp: LocalDateTime? = null
)
{

}

