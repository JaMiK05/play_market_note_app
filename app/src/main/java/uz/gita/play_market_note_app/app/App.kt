package uz.gita.play_market_note_app.app

import android.app.Application
import uz.gita.play_market_note_app.data.source.local.NoteDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        NoteDatabase.init(this)
    }

}