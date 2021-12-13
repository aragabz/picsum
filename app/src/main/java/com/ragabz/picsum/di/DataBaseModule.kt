package com.ragabz.picsum.di

import android.content.Context
import com.ragabz.picsum.data.local.dao.PictureDao
import com.ragabz.picsum.data.local.database.PicsumDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): PicsumDataBase {
        return PicsumDataBase.getDataBase(appContext)
    }

    @Provides
    fun providePictureDao(database: PicsumDataBase): PictureDao {
        return database.pictureDao()
    }
}
