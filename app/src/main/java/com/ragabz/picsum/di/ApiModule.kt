package com.ragabz.picsum.di


import com.ragabz.picsum.data.remote.api.PictureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Provides
    @Singleton
    fun providePictureApi(retrofit: Retrofit): PictureApi =
        retrofit.create(PictureApi::class.java)
}
