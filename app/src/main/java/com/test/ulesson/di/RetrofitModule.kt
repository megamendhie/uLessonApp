package com.test.ulesson.di

import com.test.ulesson.networking.BASE_URL
import com.test.ulesson.networking.ULessonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideULessonApiService(retrofit: Retrofit): ULessonApiService{
        val uLessonApiService by lazy {
            retrofit.create(ULessonApiService::class.java)
        }
        return uLessonApiService
    }
}