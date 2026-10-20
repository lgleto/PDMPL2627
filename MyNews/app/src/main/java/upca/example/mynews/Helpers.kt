package upca.example.mynews

import java.text.SimpleDateFormat
import java.util.Date

fun String.dateFromServer() : Date? {
    //2026-10-19T16:32:47Z
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    return formatter.parse(this)
}

fun Date.toShortDateTime() : String {
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
    return formatter.format(this)
}