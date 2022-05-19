package com.task.rickandmorty.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.task.rickandmorty.R
import com.task.rickandmorty.data.networck.dto.CharacterDto
import com.task.rickandmorty.data.networck.dto.InfoDto


class CharactersAdapter(private val listener: Listener) :
    RecyclerView.Adapter<CharactersAdapter.MyViewHolder>() {

    private val items = mutableListOf<CharacterDto>()

    class MyViewHolder(view: View, listener: Listener) : RecyclerView.ViewHolder(view) {

        private val itemName: TextView = view.findViewById(R.id.info_text)
        private val itemImage: ImageView = view.findViewById(R.id.image_item)
        private lateinit var characterDto: CharacterDto
        private lateinit var infoDto: InfoDto

        init {
            view.setOnClickListener {
                listener.onItemClick(characterDto)
            }
        }

        fun bindData(characterDto: CharacterDto) {
            this.characterDto = characterDto
            this.itemImage.load(characterDto.image)
            this.itemName.text = characterDto.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view, parent, false)
        return MyViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(items[position])

        if (items.size - 1 == position) {
            // request for new page
            listener.getNewPage()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(characters: List<CharacterDto>) {
        val start = this.items.size
        this.items.addAll(characters)
        notifyItemRangeInserted(start, characters.size)
    }


    interface Listener {
        fun onItemClick(characterDto: CharacterDto)
        fun getNewPage()
    }
}