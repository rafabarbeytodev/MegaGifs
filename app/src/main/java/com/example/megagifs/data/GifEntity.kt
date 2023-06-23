package com.example.megagifs.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.data
 *
 * Created by Rafael Barbeyto Torrellas on 10/06/2023 at 16:23
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Entity(
    tableName = "Gifs",
    indices = [Index(value = ["id"], unique = true)]
)

data class GifEntity(
    @PrimaryKey
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String,
    @SerializedName("favorite") val favorite: Boolean,
    @SerializedName("source") val source: String,
    @SerializedName("title") val title: String,
    @SerializedName("rating") val rating: String,
    @SerializedName("is_sticker") val is_sticker: Int,
    @SerializedName("import_datetime") val import_datetime: String,
    @SerializedName("trending_datetime") val trending_datetime: String,
    @SerializedName("user") val user: GifUser,
    @SerializedName("images") val images: GifImages,
    @SerializedName("analytics_response_payload") val analytics_response_payload: String,

)

data class GifUser(
    @SerializedName("avatar_url") val avatar_url: String,
    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("username") val username: String,
    @SerializedName("display_name") val display_name: String,
    @SerializedName("instagram_url") val instagram_url: String,
    @SerializedName("website_url") val website_url: String,
    @SerializedName("is_verified") val is_verified: Boolean
)

data class GifImages(
        @SerializedName("original") val original: GifImage,
        @SerializedName("original_mp4") val original_mp4: GifImage,
        @SerializedName("downsized") val downsized: GifImage,
        @SerializedName("downsized_large") val downsized_large: GifImage,
        @SerializedName("downsized_medium") val downsized_medium: GifImage,
        @SerializedName("downsized_small") val downsized_small: GifImage
)

data class GifImage(
        @SerializedName("height") val height: String,
        @SerializedName("width") val width: String,
        @SerializedName("size") val size: String,
        @SerializedName("url") val url: String,
        @SerializedName("mp4_size") val mp4_size: String,
        @SerializedName("mp4") val mp4: String,
)
