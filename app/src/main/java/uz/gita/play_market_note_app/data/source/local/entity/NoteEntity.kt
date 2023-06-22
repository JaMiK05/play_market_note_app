package uz.gita.play_market_note_app.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var title: String,
    var content: String,
    @ColumnInfo(name = "created_at")
    var createdAt: String,
    @ColumnInfo(name = "on_trash")
    var onTrash: Int = 0,
    var archived: Int = 0,
    var pinned: Int = 0,
    var color: Int,
)