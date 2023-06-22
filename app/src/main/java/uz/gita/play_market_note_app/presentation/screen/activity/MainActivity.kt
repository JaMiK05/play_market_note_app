package uz.gita.play_market_note_app.presentation.screen.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import uz.gita.play_market_note_app.R
import uz.gita.play_market_note_app.data.source.local.NoteDatabase
import uz.gita.play_market_note_app.databinding.ActivityMainBinding
import uz.gita.play_market_note_app.presentation.screen.fragments.archive.ArchiveFragment
import uz.gita.play_market_note_app.presentation.screen.fragments.home.HomeFragment
import uz.gita.play_market_note_app.presentation.screen.fragments.trash.TrashFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val database = NoteDatabase.getInstance().getNoteDao()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.included.apply {

            supportFragmentManager.beginTransaction().apply {
                add(R.id.container, HomeFragment())
            }.commit()

            openMenu.setOnClickListener {
                binding.root.openDrawer(GravityCompat.START)
            }
            archive.setOnClickListener {
                if (database.getArchivedNotes().isNotEmpty())
                    archive.apply {
                        animate()
                            .setDuration(100)
                            .translationX(-50f)
                            .withEndAction {
                                animate()
                                    .setDuration(100)
                                    .translationX(0f)
                                    .withEndAction {
                                        database.upArchived()
                                        cont(ArchiveFragment())
                                    }.start()
                            }.start()
                    }

            }
            trash.setOnClickListener {
                if (database.getNotesInTrash().isNotEmpty())
                    showDeleteAllDialog()
            }

        }

        binding.apply {
            navigationView.itemIconTintList = null
            navigationView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.homeFragment -> {
                        cont(HomeFragment())
                        binding.included.apply {
                            archive.visibility = View.GONE
                            trash.visibility = View.GONE
                            acName.text = "Home"
                        }

                        root.closeDrawer(GravityCompat.START)
                    }
                    R.id.archiveFragment -> {
                        cont(ArchiveFragment())
                        binding.included.apply {
                            archive.visibility = View.VISIBLE
                            trash.visibility = View.GONE
                            acName.text = "Archive"
                        }
                        root.closeDrawer(GravityCompat.START)
                    }
                    R.id.trashFragment -> {
                        cont(TrashFragment())
                        binding.included.apply {
                            trash.visibility = View.VISIBLE
                            archive.visibility = View.GONE
                            acName.text = "Trash"
                        }
                        root.closeDrawer(GravityCompat.START)
                    }

                    R.id.telegram -> {
                        startActivity(Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://t.me/jamik_gamer")))
                    }
                    R.id.instagram -> {
                        startActivity(Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://instagram.com/_mr.jamik_?igshid=ZDdkNTZiNTM=")))
                    }
                    R.id.tik -> {
                        startActivity(Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.tiktok.com/@jamik_gamer_?_t=8bhYxtZbfpS&_r=1")))
                    }

                    R.id.shared -> {
                        val appMsg: String = "Hey !, Chack out this app for Share Button :-" +
                                "https://play.google.com/store/apps/details?id=uz.gita.play_market_note_app_jamik"
                        val intent = Intent()
                        intent.action = Intent.ACTION_SEND
                        intent.putExtra(Intent.EXTRA_TEXT, appMsg)
                        intent.type = "test/plain"
                        startActivity(intent)
                    }

                }
                true
            }
        }

    }

    @SuppressLint("ResourceAsColor")
    private fun showDeleteAllDialog() {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_delete_all_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnNo: AppCompatButton = dialog.findViewById(R.id.btnNo)
        val btnYes: AppCompatButton = dialog.findViewById(R.id.btnYes)

        btnNo.setOnClickListener { dialog.dismiss() }

        btnYes.setOnClickListener {
            binding.included.trash.apply {
                animate()
                    .setDuration(100)
                    .translationX(-50f)
                    .withEndAction {
                        animate()
                            .setDuration(100)
                            .translationX(0f)
                            .withEndAction {
                                Snackbar.make(this,
                                    "Trash Cleared!!",
                                    Snackbar.LENGTH_SHORT)
                                    .setBackgroundTint(R.color.gren)
                                    .show()
                                database.deleteAllNotesInTrash()
                                cont(TrashFragment())
                            }.start()
                    }.start()
            }
            dialog.dismiss()
        }
        dialog.create()
        dialog.show()
    }

    private fun cont(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
        }.commit()
    }

}