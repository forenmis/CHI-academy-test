package com.example.chiacademytest.data.network

import com.example.chiacademytest.data.network.entity.NetworkImage
import retrofit2.http.GET
import retrofit2.http.Query

internal interface Api {
    @GET("v1/images/search")
    suspend fun getPosts(@Query("limit") limit: Int): List<NetworkImage>
}