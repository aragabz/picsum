package com.ragabz.githubapp.di

import android.content.Context
import com.ragabz.githubapp.data.local.dao.GithubRepoDao
import com.ragabz.githubapp.data.local.database.GithubDataBase
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
    fun provideDatabase(@ApplicationContext appContext: Context): GithubDataBase {
        return GithubDataBase.getDataBase(appContext)
    }

    @Provides
    fun provideUsersDao(database: GithubDataBase): GithubRepoDao {
        return database.repoDao()
    }
}
