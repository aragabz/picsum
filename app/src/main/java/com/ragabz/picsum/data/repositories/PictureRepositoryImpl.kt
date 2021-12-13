package com.ragabz.picsum.data.repositories

import com.ragabz.picsum.base.Result
import com.ragabz.picsum.data.local.datasource.PictureLocalDataSource
import com.ragabz.picsum.data.remote.datasource.PictureRemoteDataSource
import com.ragabz.picsum.di.ContextProviders
import com.ragabz.picsum.domian.repositories.PicturesRepository
import com.ragabz.picsum.models.PictureList
import com.ragabz.picsum.models.PictureModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PictureRepositoryImpl
@Inject constructor(
    private val remoteDataSource: PictureRemoteDataSource,
    private val localDataSource: PictureLocalDataSource,
    private val contextProviders: ContextProviders
) : PicturesRepository {
    override suspend fun getPictures(page: Int): Flow<Result<PictureList>> {
        return remoteDataSource.getPictures(page = page).flowOn(contextProviders.IO)
    }

    override suspend fun getCachedPictures(): Flow<Result<PictureList>> {
        val list = localDataSource.getAllPictures()
        return flowOf(Result.Success(list)).flowOn(contextProviders.IO)
    }

    override suspend fun savePicturesList(vararg list: PictureModel) {
        withContext(contextProviders.IO) {
            localDataSource.insertAll(*list)
        }
    }
}