package uz.gita.play_market_note_app.presentation.adapter

import android.annotation.SuppressLint
import android.view.*
import androidx.core.text.parseAsHtml
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.play_market_note_app.data.source.local.entity.NoteEntity
import uz.gita.play_market_note_app.databinding.ItemNoteBinding


class Adapterr : Adapter<Adapterr.Holder>() {

    private lateinit var list: List<NoteEntity>

    private var deleteLongClickListener: ((NoteEntity) -> Unit)? = null
    private var onItemClickListener: ((NoteEntity) -> Unit)? = null

    fun setOnDeleteLongClickListener(l: (NoteEntity) -> Unit) {
        deleteLongClickListener = l
    }

    fun setOnItemClickListener(l: (NoteEntity) -> Unit) {
        onItemClickListener = l
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setList(list1: List<NoteEntity>) {
        list = list1
        notifyDataSetChanged()
    }


    @SuppressLint("ClickableViewAccessibility")
    inner class Holder(val binding: ItemNoteBinding) : ViewHolder(binding.root) {

        init {

            binding.apply {

                root.setOnLongClickListener {
                    deleteLongClickListener?.invoke(list[adapterPosition])
                    true
                }

                root.setOnClickListener {
                    onItemClickListener?.invoke(list[adapterPosition])
                }

            }

        }


        fun bind() {
            val item = list[adapterPosition]
            binding.apply {
                itemNote.setBackgroundColor(item.color)
                txtTitle.text = item.title
                txtData.text = item.createdAt

                var str: StringBuilder = StringBuilder()
                if (item.content.parseAsHtml().trim().length > 40) {
                    str.append(item.content.parseAsHtml().trim().substring(0, 40))
                    str.append("...")
                } else str.append(item.content.parseAsHtml().trim())


                txtContent.text = str.toString()

                imgPin.visibility = if (item.pinned == 0) View.INVISIBLE else View.VISIBLE

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind()
    }


    override fun getItemCount() = list.size

}

