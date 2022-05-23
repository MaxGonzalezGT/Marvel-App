package com.android.data.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterMapper(
    val id : Int,
    val name : String,
    val description : String,
    val thumbnail : String,
    val thumbnailExt: String
): Parcelable {
    companion object {
        operator fun invoke(id: Int?,name: String?, description: String?,thumbnail: String?,thumbnailExt: String?) = CharacterMapper(
            id = id ?: 0,
            name = name ?: "",
            description = description ?: "",
            thumbnail = thumbnail ?: "",
            thumbnailExt = thumbnailExt ?: ""
        )
    }
}
