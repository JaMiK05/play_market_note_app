package uz.gita.play_market_note_app.presentation.screen.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.play_market_note_app.R
import uz.gita.play_market_note_app.databinding.ActivityReadWriteBinding
import uz.gita.play_market_note_app.presentation.screen.fragments.read.Read_Fragment
import uz.gita.play_market_note_app.presentation.screen.fragments.write.Write_Fragment

class Read_Write_Activity : AppCompatActivity(R.layout.activity_read_write) {

    private val binding by viewBinding(ActivityReadWriteBinding::bind)

    private val readf = Read_Fragment()

    private val writef = Write_Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = this.intent

        val screen = intent.getIntExtra("screen", 1)

        if (screen != 1) {
            binding.write.visibility = View.GONE
        }
        replace(readf)

        binding.apply {
            val visbl = View.VISIBLE
            val gone = View.GONE

            write.setOnClickListener {
                replace(writef)
                write.visibility = gone
                done.visibility = visbl
            }

            done.setOnClickListener {
                if (writef.saveToBase()) {
                    replace(readf)
                    done.visibility = gone
                    write.visibility = visbl
                }
            }

            back.setOnClickListener {
                onBackPressed()
            }

        }
    }

    private fun replace(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container1, fragment).commit()
    }


}