package com.example.desafio_ddm

object MovieRepository {
    private val movies = mutableListOf<Movie>()

    fun getAll(): MutableList<Movie> = movies

    fun add(movie: Movie) {
        movies.add(movie)
    }

    fun update(position: Int, movie: Movie) {
        movies[position] = movie
    }
}
