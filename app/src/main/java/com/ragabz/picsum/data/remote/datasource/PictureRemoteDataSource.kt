package com.ragabz.picsum.data.remote.datasource

import com.ragabz.picsum.base.Result
import com.ragabz.picsum.models.PictureList
import kotlinx.coroutines.flow.Flow

interface PictureRemoteDataSource {
    suspend fun getPictures(page: Int): Flow<Result<PictureList>>
}