package com.ragabz.picsum.data.local.datasource

import com.ragabz.picsum.data.local.dao.PictureDao
import com.ragabz.picsum.models.PictureList
import com.ragabz.picsum.models.PictureModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PictureLocalDataSourceImpl
@Inject constructor(
    private val pictureDao: PictureDao
) : PictureLocalDataSource {
    override suspend fun insertAll(vararg list: PictureModel) {
        pictureDao.insertAll(*list)
    }

    override suspend fun getAllPictures(): PictureList {
        return pictureDao.selectAllPictures()
    }
}