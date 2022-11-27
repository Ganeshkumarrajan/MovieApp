package com.anonymous.movieapp.data

import com.anonymous.movieapp.data.api.MovieService
import com.anonymous.movieapp.data.mapper.MovieToDomainMapper
import com.anonymous.movieapp.data.repository.MovieRepositoryImpl
import com.anonymous.movieapp.util.invokeResult
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
internal class MovieRepositoryImplTest {
    private lateinit var api: MovieService
    private val mockWebServer = MockWebServer()
    private lateinit var repository: MovieRepositoryImpl

    @BeforeEach
    fun setup() {
        initMockerServer()
        repository = MovieRepositoryImpl(api, MovieToDomainMapper())
    }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @DisplayName("test 200 status")
    @Test
    fun getMovieListSuccess() = runBlocking {
        mockWebServer.invokeResult("movie_list.json", 200)
        val result = repository.getMovies()
        assert(result.isNotEmpty())
    }

    private fun initMockerServer() {
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.SECONDS)
            .build()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)
    }

}
