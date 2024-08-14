package com.kafka.githubbasic.common.di

import com.kafka.githubbasic.BuildConfig
import com.kafka.githubbasic.data.user.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor {
            val request = it.request()
            val builder = request
                .newBuilder()
                .header("Authorization", "Bearer " + BuildConfig.API_KEY)
                .method(request.method, request.body)
            val mutatedRequest = builder.build()
            val response = it.proceed(mutatedRequest)
            response
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)


    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }
}