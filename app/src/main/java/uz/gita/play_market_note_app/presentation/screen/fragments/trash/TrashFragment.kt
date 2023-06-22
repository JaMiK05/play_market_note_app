package uz.gita.play_market_note_app.presentation.screen.fragments.trash

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import uz.gita.play_market_note_app.R
import uz.gita.play_market_note_app.data.source.local.NoteDatabase
import uz.gita.play_market_note_app.data.source.local.entity.NoteEntity
import uz.gita.play_market_note_app.databinding.FragmentTrashBinding
import uz.gita.play_market_note_app.presentation.adapter.Adapterr

class TrashFragment : Fragment(R.layout.fragment_trash) {

    private val binding by viewBinding(FragmentTrashBinding::bind)

    private val adap: Adapterr by lazy { Adapterr() }
    private val database = NoteDatabase.getInstance().getNoteDao()


    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            adap.setList(getList())
            recyclerTrash.apply {
                adapter = adap
                layoutManager =
                    StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
            }
            adap.setOnDeleteLongClickListener {
                showRecoverDialog(it)
            }
            adap.setOnItemClickListener {
                Snackbar.make(binding.root,
                    "To read the note, you need to restore it first",
                    Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(R.color.gren)
                    .show()
            }
        }

    }

    fun showRecoverDialog(note: NoteEntity) {
        val dialog = Dialog(requireContext())
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_recover_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnNo: AppCompatButton = dialog.findViewById(R.id.btnNo)
        val btnYes: AppCompatButton = dialog.findViewById(R.id.btnYes)

        btnNo.setOnClickListener { dialog.dismiss() }

        btnYes.setOnClickListener {
            Toast.makeText(context, "Note moved to Notes", Toast.LENGTH_SHORT).show()
            note.onTrash = 0
            database.addNote(note)
            adap.setList(getList())
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun getList(): List<NoteEntity> {
        val list = database.getNotesInTrash()
        binding.imgBin.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        return list
    }

}