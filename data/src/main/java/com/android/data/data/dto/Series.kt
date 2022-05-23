package com.android.data.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Series(
    val available: Int,
    val collectionURI: String,
    val items: @RawValue List<ItemXX>,
    val returned: Int
): Parcelable