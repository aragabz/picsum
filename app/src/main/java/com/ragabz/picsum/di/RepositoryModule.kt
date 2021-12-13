package com.ragabz.picsum.di

import com.ragabz.picsum.data.local.datasource.PictureLocalDataSource
import com.ragabz.picsum.data.remote.datasource.PictureRemoteDataSource
import com.ragabz.picsum.data.repositories.PictureRepositoryImpl
import com.ragabz.picsum.domian.repositories.PicturesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providePictureRepository(
        remoteDataSource: PictureRemoteDataSource,
        localDataSource: PictureLocalDataSource,
        contextProviders: ContextProviders
    ): PicturesRepository {
        return PictureRepositoryImpl(remoteDataSource, localDataSource, contextProviders)
    }
}
