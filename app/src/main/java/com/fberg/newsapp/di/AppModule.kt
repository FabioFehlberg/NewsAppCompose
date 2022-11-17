package com.fberg.newsapp.di

import com.fberg.newsapp.BuildConfig
import com.fberg.newsapp.common.Constants.BASE_URL
import com.fberg.newsapp.feature.data.remote.NewsService
import com.fberg.newsapp.feature.data.repository.NewsRepositoryImpl
import com.fberg.newsapp.feature.domain.repository.NewsRepository
import com.fberg.newsapp.feature.domain.use_case.GetHeadlinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNewsService(): NewsService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NewsService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsService: NewsService): NewsRepository {
        return NewsRepositoryImpl(newsService)
    }

    @Provides
    @Singleton
    fun provideHeadlinesUseCase(repository: NewsRepository): GetHeadlinesUseCase {
        return GetHeadlinesUseCase(repository)
    }
}
