package com.android.data.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: @RawValue List<Item>,
    val returned: Int
): Parcelable