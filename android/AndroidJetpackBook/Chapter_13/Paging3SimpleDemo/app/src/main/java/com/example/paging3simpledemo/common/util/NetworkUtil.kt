package com.example.paging3simpledemo.common.util

import com.example.paging3simpledemo.common.entity.ResponseEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class NetworkUtil {
    private val BASE_URL = "https://reqres.in/api/"
    private val retrofit: Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    private val retrofitService: HttpApi
    suspend fun getUserData(pageNum: Int, pageSize: Int): ResponseEntity {
        return retrofitService.getUserData(pageNum, pageSize)
    }

    internal interface HttpApi {
        @GET("users")
        suspend fun getUserData(
            @Query("page") pageNum: Int,
            @Query("per_page") pageSize: Int
        ): ResponseEntity
    }

    companion object {
        var instance: NetworkUtil? = null
            get() {
                if (field == null) {
                    field = NetworkUtil()
                }
                return field
            }
            private set
    }

    init {
        retrofitService = retrofit.create(HttpApi::class.java)
    }
}