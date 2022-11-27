package com.anonymous.movieapp.data

import com.anonymous.movieapp.data.mapper.MovieToDomainMapper
import com.anonymous.movieapp.data.model.Movie
import com.anonymous.movieapp.data.model.Result
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MovieToDomainMapperTest {
    @Test
    fun convertToDomain() {
        val result = MovieToDomainMapper().map(getFakeMovie())
        assert(result.size == 2)
        assert(result[0].id.compareTo(12345) == 0)
        assert(result[0].title == "this is title 1")
        assert(result[0].imageURL == "$imageDomainName/imageName1.png")
        assert(result[0].releasedYear == "2022")
        assert(result[1].releasedYear == "")
    }

    private fun getFakeMovie() =
        Movie(1, getFakeResult())


    private fun getFakeResult(): List<Result> =
        listOf(
            Result(
                title = "this is title 1",
                posterPath = "/imageName1.png",
                id = 12345,
                releaseDate = "2022-11-16"
            ),
            Result(title = "this is title 2", posterPath = "/imageName2.png", id = 12346),
        )

}
