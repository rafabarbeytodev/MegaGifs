package com.example.megagifs.principalscreen.data.network.response

import com.google.gson.annotations.SerializedName

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screen6.data.network.response
 *
 * Created by Rafael Barbeyto Torrellas on 01/06/2023 at 16:46
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
data class GifsResponse(
    @SerializedName("data") val data: List<GiphyItem>,
    @SerializedName("pagination") val pagination: Pagination,
    @SerializedName("meta") val meta: Meta
)

data class GiphyItem(
    @SerializedName("type") val type: String,
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("bitly_gif_url") val bitly_gif_url: String,
    @SerializedName("bitly_url") val bitly_url: String,
    @SerializedName("embed_url") val embed_url: String,
    @SerializedName("username") val username: String,
    @SerializedName("source") val source: String,
    @SerializedName("title") val title: String,
    @SerializedName("rating") val rating: String,
    @SerializedName("content_url") val content_url: String,
    @SerializedName("source_tld") val source_tld: String,
    @SerializedName("source_post_url") val source_post_url: String,
    @SerializedName("is_sticker") val is_sticker: Int,
    @SerializedName("import_datetime") val import_datetime: String,
    @SerializedName("trending_datetime") val trending_datetime: String,
    @SerializedName("images") val images: GiphyImages
)

data class GiphyImages(
    @SerializedName("original") val original: GiphyImage,
    @SerializedName("downsized") val downsized: GiphyImage,
    @SerializedName("downsized_large") val downsized_large: GiphyImage,
    @SerializedName("downsized_medium") val downsized_medium: GiphyImage,
    @SerializedName("downsized_small") val downsized_small: GiphySmallImage,
    @SerializedName("downsized_still") val downsized_still: GiphyImage,
    @SerializedName("fixed_height") val fixed_height: GiphyImage,
    @SerializedName("fixed_height_downsampled") val fixed_height_downsampled: GiphyImage,
    @SerializedName("fixed_height_small") val fixed_height_small: GiphyImage,
    @SerializedName("fixed_height_small_still") val fixed_height_small_still: GiphyImage,
    @SerializedName("fixed_height_still") val fixed_height_still: GiphyImage,
    @SerializedName("fixed_width") val fixed_width: GiphyImage,
    @SerializedName("fixed_width_downsampled") val fixed_width_downsampled: GiphyImage,
    @SerializedName("fixed_width_small") val fixed_width_small: GiphyImage,
    @SerializedName("fixed_width_small_still") val fixed_width_small_still: GiphyImage,
    @SerializedName("fixed_width_still") val fixed_width_still: GiphyImage
)

data class GiphyImage(
    @SerializedName("height") val height: String,
    @SerializedName("width") val width: String,
    @SerializedName("size") val size: String,
    @SerializedName("url") val url: String,
    @SerializedName("mp4_size") val mp4_size: String,
    @SerializedName("mp4") val mp4: String,
    @SerializedName("webp_size") val webp_size: String,
    @SerializedName("webp") val webp: String,
    @SerializedName("frames") val frames: String,
    @SerializedName("hash") val hash: String
)

data class GiphySmallImage(
    @SerializedName("height") val height: String,
    @SerializedName("width") val width: String,
    @SerializedName("mp4_size") val mp4_size: String,
    @SerializedName("mp4") val mp4: String
)
data class Pagination(
    @SerializedName("total_count") val total_count: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("offset") val offset: Int
)
data class Meta(
    @SerializedName("status") val status: Int,
    @SerializedName("msg") val msg: String,
    @SerializedName("response_id") val response_id: String
)