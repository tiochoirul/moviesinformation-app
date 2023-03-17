package com.tcm.core.utils

import com.tcm.core.data.source.local.entity.PopularEntity
import com.tcm.core.data.source.remote.response.ResultsItem
import com.tcm.core.domain.model.Popular

object DataMapper {
    fun mapResponsesToEntities(input: List<ResultsItem>): List<PopularEntity> {
        val popularList = ArrayList<PopularEntity>()
        input.map {
            val popular = PopularEntity(
                id = it.id,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                genreIds = it.genreIds,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                adult = it.adult,
                voteCount = it.voteCount,
                isFavorite = false
            )
            popularList.add(popular)
        }
        return popularList
    }

    fun mapEntitiesToDomain(input: List<PopularEntity>): List<Popular> =
        input.map {
            Popular(
                id = it.id,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                video = it.video,
                title = it.title,
                genreIds = it.genreIds,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                adult = it.adult,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Popular) = PopularEntity(
        id = input.id,
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        video = input.video,
        title = input.title,
        genreIds = input.genreIds,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        popularity = input.popularity,
        voteAverage = input.voteAverage,
        adult = input.adult,
        voteCount = input.voteCount,
        isFavorite = false
    )
}