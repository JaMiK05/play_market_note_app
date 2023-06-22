package uz.gita.play_market_note_app.domain.repository

import androidx.lifecycle.LiveData
import uz.gita.play_market_note_app.data.source.local.entity.NoteEntity

interface AppRepository {

    fun addNote(note: NoteEntity)

    fun deleteNote(note: NoteEntity)

    fun deleteNotes(notes: NoteEntity)

    fun getNotes(): LiveData<List<NoteEntity>>

    fun getArchivedNotes(): LiveData<List<NoteEntity>>

    fun getNotesInTrash(): LiveData<List<NoteEntity>>

    fun deleteAllNotesInTrash()

    fun searchNote(note: String): LiveData<List<NoteEntity>>

    fun getSearchedNote(note: String): LiveData<List<NoteEntity>>
}