package com.minafkamel.latest.data

import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class LocalDataSourceTest {

    private var apiCache: ApiCache = mock()

    private lateinit var sut: LocalDataSource

    @Before
    fun setUp() {
        sut = LocalDataSource(apiCache)
    }

    @Test
    fun `_moviesFlow emits when saveMovies is called`() = runTest {
        // Arrange
        val movies =
            listOf(Response.Movie(123L, "movie title", "10.10.2025", "url", "movie overview"))
        whenever(apiCache.getAll<Response.Movie>()).thenReturn(movies)

        // Act
        sut.saveMovies(movies)

        // Assert
        sut.moviesFlow.test {
            assertEquals(movies, awaitItem())
        }
    }

    @Test
    fun `_moviesFlow emits filtered movies when saveMovies is called`() = runTest {
        // Arrange
        val movie1 = Response.Movie(123L, "movie title 1", "10.10.2025", "url1", "movie overview 1")
        val movie2 = Response.Movie(123L, "movie title 2", "3.5.2025", "url2", "movie overview 2")
        val movie3 = Response.Movie(123L, "movie title 3", "8.6.2025", "url3", "movie overview 3")
        val movie23 = Response.Movie(123L, "movie title 23", "9.16.2025", "url23", "movie overview 23")
        whenever(apiCache.getAll<Response.Movie>()).thenReturn(listOf(movie1, movie2, movie3, movie23))

        // Act
        sut.filter("3")

        // Assert
        sut.moviesFlow.test {
            assertEquals(listOf(movie3, movie23), awaitItem())
        }
    }
}
