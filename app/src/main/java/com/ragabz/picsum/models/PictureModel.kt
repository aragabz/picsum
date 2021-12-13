package com.ragabz.picsum.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

typealias PictureList = List<PictureModel>


@Entity
data class PictureModel(
    @SerializedName("author")
    @ColumnInfo
    var author: String,
    @SerializedName("download_url")
    @ColumnInfo
    var downloadUrl: String,
    @SerializedName("height")
    @ColumnInfo
    var height: Int,
    @SerializedName("id")
    @PrimaryKey
    var id: String,
    @SerializedName("url")
    @ColumnInfo
    var url: String,
    @SerializedName("width")
    @ColumnInfo
    var width: Int
)