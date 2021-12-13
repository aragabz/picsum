package com.ragabz.picsum.di

import com.ragabz.picsum.data.local.dao.PictureDao
import com.ragabz.picsum.data.local.datasource.PictureLocalDataSource
import com.ragabz.picsum.data.local.datasource.PictureLocalDataSourceImpl
import com.ragabz.picsum.data.remote.api.PictureApi
import com.ragabz.picsum.data.remote.datasource.PictureRemoteDataSource
import com.ragabz.picsum.data.remote.datasource.PictureRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideGithubRepoRemoteDataSource(pictureApi: PictureApi): PictureRemoteDataSource =
        PictureRemoteDataSourceImpl(pictureApi)


    @Provides
    @Singleton
    fun provideGithubRepoLocalDataSource(pictureDao: PictureDao): PictureLocalDataSource =
        PictureLocalDataSourceImpl(pictureDao)
}