package com.android.data.util

import androidx.recyclerview.widget.DiffUtil
import com.android.data.data.database.entities.CharacterEntity

class CharactersDiffUtil(
    private val oldList: List<CharacterEntity>,
    private val newList: List<CharacterEntity>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
}