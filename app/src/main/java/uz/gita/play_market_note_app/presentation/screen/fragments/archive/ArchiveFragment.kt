package uz.gita.play_market_note_app.presentation.screen.fragments.archive

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.play_market_note_app.R
import uz.gita.play_market_note_app.databinding.FragmentArchiveBinding
import uz.gita.play_market_note_app.data.model.Id
import uz.gita.play_market_note_app.data.source.local.NoteDatabase
import uz.gita.play_market_note_app.data.source.local.entity.NoteEntity
import uz.gita.play_market_note_app.presentation.adapter.Adapterr
import uz.gita.play_market_note_app.presentation.screen.activity.Read_Write_Activity

class ArchiveFragment : Fragment(R.layout.fragment_archive) {

    private val binding by viewBinding(FragmentArchiveBinding::bind)

    private val adap: Adapterr by lazy { Adapterr() }
    private val database = NoteDatabase.getInstance().getNoteDao()


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            adap.setList(getList())

            adap.apply {
                setOnItemClickListener {
                    Id.getInstance().id = it.id
                    startActivity(
                        Intent(requireActivity(), Read_Write_Activity::class.java)
                            .putExtra("screen", 2))
                }
                setOnDeleteLongClickListener { note ->
                    val dialog = Dialog(requireContext())
                    dialog.setCancelable(false)
                    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    dialog.setContentView(R.layout.custom_delete_all_dialog)

                    dialog.findViewById<AppCompatTextView>(R.id.txt).text =
                        "Do you want to remove it from the archive?"
                    val no = dialog.findViewById<AppCompatButton>(R.id.btnNo)
                    val yes = dialog.findViewById<AppCompatButton>(R.id.btnYes)

                    no.setOnClickListener {
                        dialog.dismiss()
                    }

                    yes.setOnClickListener {
                        note.archived = 0
                        database.addNote(note)
                        adap.setList(getList())
                        dialog.dismiss()
                    }
                    dialog.show()

                }
            }

            recyclerArchive.adapter = adap
            recyclerArchive.layoutManager =
                StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)

        }

    }

    private fun getList(): List<NoteEntity> {
        val list = database.getArchivedNotes()
        binding.imgBin.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        return list
    }

}