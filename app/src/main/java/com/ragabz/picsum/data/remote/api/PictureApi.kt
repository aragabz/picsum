package com.ragabz.picsum.data.remote.api

import com.ragabz.picsum.models.PictureList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val LIMIT = 10

interface PictureApi {

    /**
     * Fetch pictures with pagination
     *
     * @param page
     * @param limit
     * @return [Response<PictureList>]
     */
    @GET("list")
    suspend fun fetchPictures(
        @Query("page") page: Int,
        @Query("limit") limit: Int = LIMIT
    ): Response<PictureList>
}