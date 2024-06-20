package com.example.mylibrary.movies.domain

import javax.inject.Inject

class GetTop100MoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke() = moviesRepository.getTop100Movies()
}