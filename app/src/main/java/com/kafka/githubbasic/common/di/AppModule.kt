package com.kafka.githubbasic.common.di

import com.kafka.githubbasic.data.user.UserRepositoryImplementation
import com.kafka.githubbasic.data.user.datasources.NetworkUserDataSource
import com.kafka.githubbasic.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        networkUserDataSource: NetworkUserDataSource
    ): UserRepository {
        return UserRepositoryImplementation(
            networkUserDataSource
        )
    }
}