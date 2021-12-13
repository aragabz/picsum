package com.ragabz.picsum.domian.repositories

import com.ragabz.picsum.base.Result
import com.ragabz.picsum.models.PictureList
import com.ragabz.picsum.models.PictureModel
import kotlinx.coroutines.flow.Flow

interface PicturesRepository {
    /**
     * Fetch pictures by page from remote
     *
     * @param page
     * @return
     */
    suspend fun getPictures(page: Int): Flow<Result<PictureList>>

    /**
     * Get cached pictures from database
     *
     * @return
     */
    suspend fun getCachedPictures(): Flow<Result<PictureList>>

    /**
     * Save pictures list
     *
     * @param list
     */
    suspend fun savePicturesList(vararg list: PictureModel)
}