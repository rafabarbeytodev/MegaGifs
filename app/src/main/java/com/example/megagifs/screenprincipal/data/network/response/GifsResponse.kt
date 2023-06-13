package com.example.megagifs.screenprincipal.data.network.response

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
    @SerializedName("data") val data: List<GifItem>
)
data class GifItem(
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
    @SerializedName("images") val images: GifImages,
    @SerializedName("analytics_response_payload") val analytics_response_payload: String,
    @SerializedName("user") val user: GifUser,
    @SerializedName("analytics") val analytics: GifAnalytics
)

data class GifAnalytics(
    @SerializedName("onload") val onload: GifAnalytic,
    @SerializedName("onclick") val onclick: GifAnalytic,
    @SerializedName("onsent") val onsent: GifAnalytic
)
data class GifAnalytic(
    @SerializedName("url") val url: String
)

data class GifUser(
    @SerializedName("avatar_url") val avatar_url: String,
    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("banner_url") val banner_url: String,
    @SerializedName("profile_url") val profile_url: String,
    @SerializedName("username") val username: String,
    @SerializedName("display_name") val display_name: String,
    @SerializedName("description") val description: String,
    @SerializedName("instagram_url") val instagram_url: String,
    @SerializedName("website_url") val website_url: String,
    @SerializedName("is_verified") val is_verified: Boolean
)

data class GifImages(
    @SerializedName("original") val original: GifImage,
    @SerializedName("downsized") val downsized: GifImage,
    @SerializedName("downsized_large") val downsized_large: GifImage,
    @SerializedName("downsized_medium") val downsized_medium: GifImage,
    @SerializedName("downsized_still") val downsized_still: GifImage,
    @SerializedName("fixed_height") val fixed_height: GifImage,
    @SerializedName("fixed_height_downsampled") val fixed_height_downsampled: GifImage,
    @SerializedName("fixed_height_small") val fixed_height_small: GifImage,
    @SerializedName("fixed_height_small_still") val fixed_height_small_still: GifImage,
    @SerializedName("fixed_height_still") val fixed_height_still: GifImage,
    @SerializedName("fixed_width") val fixed_width: GifImage,
    @SerializedName("fixed_width_downsampled") val fixed_width_downsampled: GifImage,
    @SerializedName("fixed_width_small") val fixed_width_small: GifImage,
    @SerializedName("fixed_width_small_still") val fixed_width_small_still: GifImage,
    @SerializedName("fixed_width_still") val fixed_width_still: GifImage,
    @SerializedName("preview_gif") val preview_gif: GifImage
)

data class GifImage(
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
