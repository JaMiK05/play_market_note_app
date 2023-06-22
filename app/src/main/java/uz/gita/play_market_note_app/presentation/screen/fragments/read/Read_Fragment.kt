package uz.gita.play_market_note_app.presentation.screen.fragments.read

import android.os.Bundle
import android.view.View
import androidx.core.text.parseAsHtml
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.play_market_note_app.R
import uz.gita.play_market_note_app.data.model.Id
import uz.gita.play_market_note_app.data.source.local.NoteDatabase
import uz.gita.play_market_note_app.data.source.local.entity.NoteEntity
import uz.gita.play_market_note_app.databinding.FragmentReadBinding

class Read_Fragment : Fragment(R.layout.fragment_read_) {

    private val binding by viewBinding(FragmentReadBinding::bind)

    val database = NoteDatabase.getInstance().getNoteDao()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        des()

    }

    override fun onResume() {
        super.onResume()
        des()
    }

    private fun des() {
        val note = getId1()
        binding.apply {
            title.text = note.title
            desc.text = note.content.parseAsHtml().toString().trim()
        }
    }

    private fun getId1(): NoteEntity {
        return database.getNote(Id.getInstance().id)
    }

}