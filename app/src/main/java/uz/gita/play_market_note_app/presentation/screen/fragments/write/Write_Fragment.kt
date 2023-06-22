package uz.gita.play_market_note_app.presentation.screen.fragments.write

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.text.parseAsHtml
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.richeditor.RichEditor
import uz.gita.play_market_note_app.R
import uz.gita.play_market_note_app.data.model.Id
import uz.gita.play_market_note_app.data.source.local.NoteDatabase
import uz.gita.play_market_note_app.data.source.local.entity.NoteEntity
import uz.gita.play_market_note_app.databinding.FragmentWriteBinding
import uz.gita.play_market_note_app.utils.Colors

class Write_Fragment : Fragment(R.layout.fragment_write) {

    private val binding by viewBinding(FragmentWriteBinding::bind)

    private lateinit var editor: RichEditor


    private val database = NoteDatabase.getInstance().getNoteDao()

    private val note: NoteEntity = database.getNote(Id.getInstance().id)

    private var titlle = ""
    private var desc = ""
    private var colorr = note.color

    @SuppressLint("ResourceAsColor")
    fun saveToBase(): Boolean {
        if (binding.edtTitle.text.toString().isNotEmpty() && binding.richEditor.html.toString()
                .isNotEmpty()
        ) {
            note.apply {
                title = binding.edtTitle.text.toString()
                color = colorr
                content = binding.richEditor.html.trim()
            }
            database.addNote(note)
            return true
        } else {
            Snackbar.make(binding.root,
                "Check if the fields are not empty!!!",
                Snackbar.LENGTH_SHORT)
                .setBackgroundTint(ContextCompat.getColor(requireContext(),
                    R.color.background_primary))
                .show()
            return false
        }
    }


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        editor = binding.richEditor
        editor.setPlaceholder("Insert note here...")
        editor.html = note.content.parseAsHtml().toString().trim()
        editor.setPadding(0, 8, 0, 8)

        binding.apply {
            edtTitle.setText(note.title)
            imgColor.setOnClickListener {
                showSetColorDialog()
            }
        }

        clickEvents()
        backpress()

    }

    private fun backpress() {
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val dialog = Dialog(requireContext())
                dialog.setCancelable(false)
                dialog.setContentView(R.layout.dialog_back)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                val no = dialog.findViewById<AppCompatButton>(R.id.btnNod)
                val yes = dialog.findViewById<AppCompatButton>(R.id.btnYesd)

                no.setOnClickListener {
                    requireActivity().finish()
                    dialog.dismiss()
                }

                yes.setOnClickListener {
                    if (saveToBase()) {
                        requireActivity().finish()
                    } else {
                        dialog.dismiss()
                    }
                }

                dialog.show()

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun clickEvents() {
        binding.apply {
            actionBold.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setBold()
            }

            actionItalic.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setItalic()
            }
            actionHeading1.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setHeading(1)
            }

            actionHeading2.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setHeading(2)
            }

            actionHeading3.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setHeading(3)
            }

            actionHeading4.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setHeading(4)
            }

            actionHeading5.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setHeading(5)
            }

            actionHeading6.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setHeading(6)
            }

            actionIndent.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setIndent()
            }

            actionOutdent.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setOutdent()
            }

            actionUnderline.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setUnderline()
            }

            actionStrikethrough.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setStrikeThrough()
            }

            actionAlignLeft.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setAlignLeft()
            }

            actionAlignCenter.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setAlignCenter()
            }

            actionAlignRight.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setAlignRight()
            }

            actionBlockquote.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(requireContext(), R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(requireContext(),
                        R.color.blue))
                }
                editor.setBullets()
            }
        }
    }

    private fun showSetColorDialog() {
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

        red.setOnClickListener {
            colorr = colorOjb.red
            dialog.dismiss()
        }

        white.setOnClickListener {
            colorr = colorOjb.white
            dialog.dismiss()
        }

        pink.setOnClickListener {
            colorr = colorOjb.dark_pink
            dialog.dismiss()
        }

        orange.setOnClickListener {
            colorr = colorOjb.orange
            dialog.dismiss()
        }

        yellow.setOnClickListener {
            colorr = colorOjb.yellow
            dialog.dismiss()
        }

        green.setOnClickListener {
            colorr = colorOjb.green
            dialog.dismiss()
        }

        blue.setOnClickListener {
            colorr = colorOjb.blue
            dialog.dismiss()
        }

        light_pink.setOnClickListener {
            colorr = colorOjb.light_pink
            dialog.dismiss()
        }

        dialog.create()
        dialog.show()
    }


}