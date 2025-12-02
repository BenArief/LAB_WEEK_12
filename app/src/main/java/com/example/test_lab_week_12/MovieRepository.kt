package com.example.test_lab_week_12

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_lab_week_12.api.MovieService
import com.example.test_lab_week_12.model.Movie

class MovieRepository(private val movieService: MovieService) {
    private val apiKey = "f82cd49e71d7b5af3cd1b28c2ad14ce0"

    private val movieLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = movieLiveData

    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String>
        get() = errorLiveData

    suspend fun fetchMovies(){
        try {
            val popularMovies = movieService.getPopularMovies(apiKey)
            movieLiveData.postValue(popularMovies.results)
        } catch (exception : Exception){

            errorLiveData.postValue(
                "An Error Occured: ${exception.message}"
            )
        }
    }
}