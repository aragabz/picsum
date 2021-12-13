package com.ragabz.githubapp.di

import com.ragabz.githubapp.data.local.datasource.GithubRepoLocalDataSource
import com.ragabz.githubapp.data.remote.datasource.GithubRepoRemoteDataSource
import com.ragabz.githubapp.domain.repositories.GithubRepoRepository
import com.ragabz.githubapp.data.repositories.GithubRepoRepositoryImpl
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
    fun provideGithubRepository(
        remoteDataSource: GithubRepoRemoteDataSource,
        localDataSource: GithubRepoLocalDataSource,
        contextProviders: ContextProviders
    ): GithubRepoRepository {
        return GithubRepoRepositoryImpl(remoteDataSource, localDataSource, contextProviders)
    }
}
