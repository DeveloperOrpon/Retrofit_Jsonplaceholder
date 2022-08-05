package com.example.myrecyclerview.api

import com.example.myrecyclerview.Posts
import retrofit2.Call
import retrofit2.http.GET

public interface JsonPleaseHolderAPI {
    @GET("photos")
    fun getPost(): Call<List<Posts>>
}