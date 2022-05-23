package com.android.data.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: @RawValue List<ItemXXX>,
    val returned: Int
): Parcelable