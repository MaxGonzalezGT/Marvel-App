package com.android.data.data.dto

import android.os.Parcelable
import com.android.data.domain.model.CharacterMapper
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Result(
    val comics: @RawValue Comics?,
    val description: String,
    val events: @RawValue Events?,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: @RawValue Series?,
    val stories: @RawValue Stories?,
    val thumbnail: @RawValue Thumbnail?,
    val urls: @RawValue List<Url>?
): Parcelable
{
    fun toCharacter(): CharacterMapper {
        return CharacterMapper(
            id=id,
            name= name,
            description=description,
            thumbnail = thumbnail?.path ?: "",
            thumbnailExt=thumbnail?.extension?: ""
        )
    }
}