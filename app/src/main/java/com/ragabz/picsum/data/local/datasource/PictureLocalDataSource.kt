package com.ragabz.picsum.data.local.datasource

import com.ragabz.picsum.models.PictureList
import com.ragabz.picsum.models.PictureModel
import kotlinx.coroutines.flow.Flow

interface PictureLocalDataSource {
    /**
     * Insert all
     *
     * @param list
     */
    suspend fun insertAll(vararg list: PictureModel)

    /**
     * Get all pictures
     *
     * @return
     */
    suspend fun getAllPictures(): PictureList
}