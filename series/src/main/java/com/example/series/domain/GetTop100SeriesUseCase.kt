package com.example.series.domain


import javax.inject.Inject

class GetTop100SeriesUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository
) {
    operator fun invoke() = seriesRepository.getTop100Series()
}