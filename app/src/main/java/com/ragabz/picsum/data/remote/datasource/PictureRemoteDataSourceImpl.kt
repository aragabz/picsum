package com.ragabz.picsum.data.remote.datasource

import com.ragabz.picsum.base.Result
import com.ragabz.picsum.data.remote.api.PictureApi
import com.ragabz.picsum.extensions.toFlow
import com.ragabz.picsum.models.PictureList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PictureRemoteDataSourceImpl
@Inject constructor(
    private val pictureApi: PictureApi
) : PictureRemoteDataSource {
    override suspend fun getPictures(page: Int): Flow<Result<PictureList>> {
        return pictureApi.fetchPictures(page = page).toFlow()
    }
}