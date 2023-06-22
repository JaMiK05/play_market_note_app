package uz.gita.play_market_note_app.presentation.screen.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.richeditor.RichEditor
import uz.gita.play_market_note_app.data.source.local.NoteDatabase
import uz.gita.play_market_note_app.data.source.local.converter.DateConverter
import uz.gita.play_market_note_app.data.source.local.entity.NoteEntity
import uz.gita.play_market_note_app.utils.Colors
import uz.gita.play_market_note_app.R
import uz.gita.play_market_note_app.databinding.ActivityAddNoteBinding

class Add_Note_Activity : AppCompatActivity(R.layout.activity_add_note) {

    private val binding by viewBinding(ActivityAddNoteBinding::bind)
    private val database = NoteDatabase.getInstance().getNoteDao()

    private lateinit var editor: RichEditor

    private var color = R.color.teal_700

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        editor = binding.richEditor
        editor.setPlaceholder("Insert note here...")
        editor.setPadding(0, 8, 0, 8)

        binding.apply {
            back.setOnClickListener {
               onBackPressed()
            }
            addbtn.setOnClickListener {

                val title = edtTitle.text.toString().trim()
                val content =
                    if (binding.richEditor.html != null) binding.richEditor.html.trim() else ""
                val time = DateConverter.getCurrentTime()

                if (title.isNotEmpty() && content.isNotEmpty()) {
                    database.addNote(NoteEntity(
                        title = title,
                        content = content.trim(),
                        createdAt = time,
                        color = color
                    ))
                   onBackPressed()
                } else
                    Snackbar.make(binding.root,
                        "Check if the fields are not empty!!!",
                        Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(ContextCompat.getColor(this@Add_Note_Activity,
                            R.color.background_primary))
                        .show()
            }
            imgColor.setOnClickListener {
                showSetColorDialog()
            }

        }

        clickEvents()

    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun clickEvents() {
        binding.apply {
            actionBold.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setBold()
            }

            actionItalic.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setItalic()
            }

            actionHeading1.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setHeading(1)
            }

            actionHeading2.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setHeading(2)
            }

            actionHeading3.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setHeading(3)
            }

            actionHeading4.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setHeading(4)
            }

            actionHeading5.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setHeading(5)
            }

            actionHeading6.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setHeading(6)
            }

            actionIndent.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setIndent()
            }

            actionOutdent.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setOutdent()
            }

            actionUnderline.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setUnderline()
            }

            actionStrikethrough.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setStrikeThrough()
            }

            actionAlignLeft.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setAlignLeft()
            }

            actionAlignCenter.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setAlignCenter()
            }

            actionAlignRight.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setAlignRight()
            }

            actionBlockquote.setOnClickListener {
                val color = (it.background as ColorDrawable).color
                if (color == ContextCompat.getColor(this@Add_Note_Activity, R.color.blue)) {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.redback))
                } else {
                    it.setBackgroundColor(ContextCompat.getColor(this@Add_Note_Activity,
                        R.color.blue))
                }
                editor.setBullets()
            }
        }
    }


    override fun onBackPressed() {
        binding.apply {
            val title = edtTitle.text!!
            val content = binding.richEditor.html
            if (title.isNotEmpty() && content != null) {
                inte()
            } else inte()
        }
    }

    private fun showSetColorDialog() {
        val colorOjb = Colors.getInstance(this)
        val dialog = Dialog(this)
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
            color = colorOjb.red
            dialog.dismiss()
        }

        white.setOnClickListener {
            color = colorOjb.white
            dialog.dismiss()
        }

        pink.setOnClickListener {
            color = colorOjb.dark_pink
            dialog.dismiss()
        }

        orange.setOnClickListener {
            color = colorOjb.orange
            dialog.dismiss()
        }

        yellow.setOnClickListener {
            color = colorOjb.yellow
            dialog.dismiss()
        }

        green.setOnClickListener {
            color = colorOjb.green
            dialog.dismiss()
        }

        blue.setOnClickListener {
            color = colorOjb.blue
            dialog.dismiss()
        }

        light_pink.setOnClickListener {
            color = colorOjb.light_pink
            dialog.dismiss()
        }

        dialog.create()
        dialog.show()
    }

    private fun inte() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}