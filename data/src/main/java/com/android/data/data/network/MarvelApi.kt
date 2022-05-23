package com.android.data.data.network

import com.android.data.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import com.android.data.data.dto.Character

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("apikey")apikey:String= Constants.API_KEY,
        @Query("limit")limit:String= Constants.LIMIT,
        @Query("offset")offset:String,
        @Query("ts")ts:String=Constants.timeStamp,
        @Query("hash")hash:String=Constants.hash()
    ): Character

    @GET("/v1/public/characters")
    suspend fun getSearchedCharacters(
        @Query("apikey")apikey:String= Constants.API_KEY,
        @Query("limit")limit:String= Constants.LIMIT,
        @Query("ts")ts:String=Constants.timeStamp,
        @Query("hash")hash:String=Constants.hash(),
        @Query("nameStartsWith")query:String
    ): Character
}