package com.ragabz.githubapp.di


import com.ragabz.githubapp.data.remote.api.GithubRepoApi
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
    fun provideGithubRepoApi(retrofit: Retrofit): GithubRepoApi =
        retrofit.create(GithubRepoApi::class.java)
}
