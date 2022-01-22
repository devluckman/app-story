package id.man.story.domain.extention

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */

fun Long.toDate(): String {
    try {
        val newDateFormat = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
        val date = Date(this)
        return newDateFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return ""
}