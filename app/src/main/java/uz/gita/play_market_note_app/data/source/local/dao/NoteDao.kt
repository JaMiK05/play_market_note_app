package uz.gita.play_market_note_app.data.source.local.dao

import androidx.room.*
import uz.gita.play_market_note_app.data.source.local.entity.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

    @Query("SELECT * FROM Notes WHERE id LIKE :id")
    fun getNote(id: Long): NoteEntity

    @Query("SELECT * FROM Notes WHERE on_trash=0 AND archived=0 ORDER BY pinned DESC")
    fun getNotes(): List<NoteEntity>

    @Query("SELECT * FROM Notes WHERE archived=1")
    fun getArchivedNotes(): List<NoteEntity>

    @Query("UPDATE Notes SET archived=0 WHERE archived=1")
    fun upArchived()

    @Query("SELECT * FROM Notes WHERE on_trash=1 AND archived=0")
    fun getNotesInTrash(): List<NoteEntity>

    @Query("DELETE FROM Notes WHERE on_trash = 1")
    fun deleteAllNotesInTrash()

    // @Query("SELECT * FROM Notes WHERE title LIKE :note AND on_trash=0")
    //@Query("SELECT * FROM Notes WHERE title LIKE :note || '%' AND on_trash=0")
    @Query("SELECT * FROM Notes WHERE UPPER(title) LIKE :note || '%' AND on_trash=0 And archived=0 ORDER BY UPPER(title) ASC")
    fun searchNote(note: String): List<NoteEntity>

}