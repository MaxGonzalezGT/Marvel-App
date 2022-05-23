package com.android.data.util

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {

    companion object {

        // Marvel API
        const val BASE_URL = "BASE_URL"
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "API_KEY"
        const val PRIVATE_KEY = "PRIVATE_KEY
        const val LIMIT = "100"
        fun hash(): String {
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }

        // Preferences
        const val PREFERENCES_NAME = "marvel_preferences"
        const val PREFERENCES_BACK_ONLINE = "backOnline"

        // Bundles
        const val CHARACTER_RESULT_KEY = "characterBundle"

        // ROOM Database
        const val DATABASE_NAME = "character_database"
        const val CHARACTER_TABLE = "character_table"

        // Handle Error
        const val CHARACTER_NOT_FOUND = "Characters not found."

    }
}