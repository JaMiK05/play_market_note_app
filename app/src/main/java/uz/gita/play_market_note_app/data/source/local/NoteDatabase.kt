package uz.gita.play_market_note_app.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.play_market_note_app.data.source.local.dao.NoteDao
import uz.gita.play_market_note_app.data.source.local.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {

        private lateinit var database: NoteDatabase

        private const val DATABSE_NAME = "NoteDB.db"

        fun init(context: Context) {
            if (!(Companion::database.isInitialized)) {
                database = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    DATABSE_NAME
                )
                    .allowMainThreadQueries()
                    .build()
            }
        }

        fun getInstance() = database

    }

}