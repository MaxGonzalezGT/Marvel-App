package com.android.marvel.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.data.util.CharactersDiffUtil
import com.android.marvel.databinding.CharactersRowLayoutBinding


class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.MyViewHolder>() {

    private var characters = emptyList<com.android.data.data.database.entities.CharacterEntity>()

    class MyViewHolder(private val binding: CharactersRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: com.android.data.data.database.entities.CharacterEntity){
            binding.result = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CharactersRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentCharacter = characters[position]
        holder.bind(currentCharacter)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    fun setData(newData: List<com.android.data.data.database.entities.CharacterEntity>){
        val characterDiffUtil =
            CharactersDiffUtil(characters, newData)
        DiffUtil.calculateDiff(characterDiffUtil)
        var list = newData
        characters = list.distinctBy { it.id }
        notifyDataSetChanged()
    }
}