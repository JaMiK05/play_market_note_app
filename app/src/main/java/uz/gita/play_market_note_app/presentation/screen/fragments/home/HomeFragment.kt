package uz.gita.play_market_note_app.presentation.screen.fragments.home


import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.gita.play_market_note_app.data.model.Id
import uz.gita.play_market_note_app.data.source.local.NoteDatabase
import uz.gita.play_market_note_app.data.source.local.entity.NoteEntity
import uz.gita.play_market_note_app.presentation.adapter.Adapterr
import uz.gita.play_market_note_app.presentation.screen.activity.Add_Note_Activity
import uz.gita.play_market_note_app.presentation.screen.activity.Read_Write_Activity
import uz.gita.play_market_note_app.utils.Colors
import uz.gita.play_market_note_app.R
import uz.gita.play_market_note_app.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val adap: Adapterr by lazy { Adapterr() }
    private val database = NoteDatabase.getInstance().getNoteDao()
    private var searc = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            adap.setList(getList())
            adap.apply {
                setOnItemClickListener {
                    Id.getInstance().id = it.id
                    startActivity(
                        Intent(requireActivity(), Read_Write_Activity::class.java)
                            .putExtra("screen", 1))
                }
                setOnDeleteLongClickListener {
                    showBottomSheetDialog(it)
                }
            }
            recycler.adapter = adap
            recycler.layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)

            addNoteBtn.setOnClickListener {
                addNoteBtn.apply {
                    animate()
                        .setDuration(100)
                        .rotation(-180f)
                        .withEndAction {
                            rotation = 0f
                            requireActivity().startActivity(Intent(
                                requireContext(),
                                Add_Note_Activity::class.java))
                            requireActivity().finish()
                        }
                }
            }

            search.doAfterTextChanged {
                searc = search.text.toString()
                adap.setList(getList())
            }

            imgsearch.setOnClickListener {

                imgsearch.apply {
                    animate()
                        .setDuration(300)
                        .rotationY(360f)
                        .withEndAction {
                            rotationY = 0f
                        }.start()
                }

            }


        }

    }

    private fun showSetColorDialog(note: NoteEntity) {

        val colorOjb = Colors.getInstance(requireContext())
        val dialog = Dialog(requireContext())
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_colors_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val white = dialog.findViewById<CardView>(R.id.white)
        val pink = dialog.findViewById<CardView>(R.id.pink)
        val red = dialog.findViewById<CardView>(R.id.red)
        val orange = dialog.findViewById<CardView>(R.id.orange)
        val yellow = dialog.findViewById<CardView>(R.id.yellow)
        val green = dialog.findViewById<CardView>(R.id.green)
        val blue = dialog.findViewById<CardView>(R.id.blue)
        val light_pink = dialog.findViewById<CardView>(R.id.light_pink)

        white.setOnClickListener {
            note.color = colorOjb.white
            adap.setList(getList())
            dialog.dismiss()
        }

        red.setOnClickListener {
            note.color = colorOjb.red
            database.addNote(note)
            adap.setList(getList())
            dialog.dismiss()
        }

        pink.setOnClickListener {
            note.color = colorOjb.dark_pink
            database.addNote(note)
            adap.setList(getList())
            dialog.dismiss()
        }

        orange.setOnClickListener {
            note.color = colorOjb.orange
            database.addNote(note)
            adap.setList(getList())
            dialog.dismiss()
        }

        yellow.setOnClickListener {
            note.color = colorOjb.yellow
            database.addNote(note)
            adap.setList(getList())
            dialog.dismiss()
        }

        green.setOnClickListener {
            note.color = colorOjb.green
            database.addNote(note)
            adap.setList(getList())
            dialog.dismiss()
        }

        blue.setOnClickListener {
            note.color = colorOjb.blue
            database.addNote(note)
            adap.setList(getList())
            dialog.dismiss()
        }

        light_pink.setOnClickListener {
            note.color = colorOjb.light_pink
            database.addNote(note)
            adap.setList(getList())
            dialog.dismiss()
        }
        dialog.create()
        dialog.show()
    }

    fun showBottomSheetDialog(note: NoteEntity) {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setCancelable(false)
        val view = dialog.layoutInflater.inflate(R.layout.home_bottomsheet_dialog, null)

        val btnLine = view.findViewById<LinearLayoutCompat>(R.id.line)
        val btnPin = view.findViewById<LinearLayoutCompat>(R.id.linePin)
        val btnColor = view.findViewById<LinearLayoutCompat>(R.id.lineColor)
        val btnArchive = view.findViewById<LinearLayoutCompat>(R.id.lineArchive)
        val btnDelete = view.findViewById<LinearLayoutCompat>(R.id.lineDelete)

        btnLine.setOnClickListener {
            dialog.dismiss()
        }

        btnPin.setOnClickListener {
            if (note.pinned == 0) {
                note.pinned = 1
                database.addNote(note)
            } else {
                note.pinned = 0
                database.addNote(note)
            }
            adap.setList(getList())
            dialog.dismiss()
        }

        btnColor.setOnClickListener {
            showSetColorDialog(note)
            dialog.dismiss()
        }

        btnArchive.setOnClickListener {
            Toast.makeText(context, "Note moved to Archive", Toast.LENGTH_SHORT).show()
            note.apply {
                archived = 1
                pinned = 0
            }
            database.addNote(note)
            adap.setList(getList())
            dialog.dismiss()
        }

        btnDelete.setOnClickListener {
            Toast.makeText(context, "Note moved to Bin", Toast.LENGTH_SHORT).show()
            note.apply {
                onTrash = 1
                archived = 0
                pinned = 0
            }
            database.addNote(note)
            adap.setList(getList())
            dialog.dismiss()
        }
        dialog.setContentView(view)
        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        adap.setList(getList())
    }

    private fun getList(): List<NoteEntity> {
        val list = if (searc.isEmpty()) database.getNotes() else database.searchNote(searc)
        binding.empty.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        return list
    }


}