package uz.gita.play_market_note_app.data.source.local.converter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateConverter {
    @TypeConverter
    fun fromDateToTimeStamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromTimeStampToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return current.format(formatter)
    }
}