package com.tcm.core.data.source.local.entity

import androidx.room.*
import com.tcm.core.utils.IntConverters

@Entity(tableName = "popular")
@TypeConverters(IntConverters::class)
data class PopularEntity(

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "original_language")
    var originalLanguage: String,

    @ColumnInfo(name = "original_title")
    var originalTitle: String,

    @ColumnInfo(name = "video")
    var video: Boolean,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "genre_ids")
    var genreIds: List<Int>,

    @ColumnInfo(name = "poster_path")
    var posterPath: String,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String,

    @ColumnInfo(name = "release_date")
    var releaseDate: String,

    @ColumnInfo(name = "popularity")
    var popularity: Double,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double,

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "adult")
    var adult: Boolean,

    @ColumnInfo(name = "vote_count")
    var voteCount: Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)