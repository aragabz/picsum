package com.ragabz.githubapp.di

import com.ragabz.githubapp.data.local.dao.GithubRepoDao
import com.ragabz.githubapp.data.local.datasource.GithubRepoLocalDataSource
import com.ragabz.githubapp.data.local.datasource.GithubRepoLocalDataSourceImpl
import com.ragabz.githubapp.data.remote.api.GithubRepoApi
import com.ragabz.githubapp.data.remote.datasource.GithubRepoRemoteDataSource
import com.ragabz.githubapp.data.remote.datasource.GithubRepoRemoteDataSourceImpl
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
    fun provideGithubRepoRemoteDataSource(githubRepoApi: GithubRepoApi): GithubRepoRemoteDataSource =
        GithubRepoRemoteDataSourceImpl(githubRepoApi)


    @Provides
    @Singleton
    fun provideGithubRepoLocalDataSource(githubRepoDao: GithubRepoDao): GithubRepoLocalDataSource =
        GithubRepoLocalDataSourceImpl(githubRepoDao)
}