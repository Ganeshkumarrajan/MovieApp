package com.anonymous.movieapp.di

import com.anonymous.movieapp.BuildConfig
import com.anonymous.movieapp.data.api.MovieService
import com.anonymous.movieapp.data.mapper.MovieToDomainMapper
import com.anonymous.movieapp.data.repository.MovieRepositoryImpl
import com.anonymous.movieapp.domain.movieDetails.model.GetMovieDetailsUseCase
import com.anonymous.movieapp.domain.movieList.repository.MovieRepository
import com.anonymous.movieapp.domain.movieList.useCase.GetMovieListUseCase
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().apply {
            this.baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
            this.client(httpClient)
        }.build()

    @Singleton
    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            this.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    @Singleton
    @Provides
    fun provideOKHTTPClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Singleton
    @Provides
    fun provideGetMovieListUseCase(repository: MovieRepository): GetMovieListUseCase =
        GetMovieListUseCase(repository)

    @Singleton
    @Provides
    fun provideRepository(
        service: MovieService,
        mapper: MovieToDomainMapper
    ): MovieRepository =
        MovieRepositoryImpl(service, mapper)

    @Singleton
    @Provides
    fun provideGetMovieDetailsUseCase(repository: MovieRepository): GetMovieDetailsUseCase =
        GetMovieDetailsUseCase(repository)

    @Singleton
    @Provides
    fun provideMapper(): MovieToDomainMapper =
        MovieToDomainMapper()
}
