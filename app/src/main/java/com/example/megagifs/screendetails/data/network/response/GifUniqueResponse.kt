package com.example.megagifs.screendetails.data.network.response

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
data class GifUniqueResponse(
    @SerializedName("data") val data: GifUniqueItem,
    @SerializedName("meta") val meta: MetaUnique
)

data class GifUniqueItem(
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
    @SerializedName("images") val images: GifUniqueImages,
    @SerializedName("user") val user: GifUniqueUser,
    @SerializedName("analytics") val analytics: GifUniqueAnalytics,
    @SerializedName("analytics_response_payload") val analytics_response_payload: String
)

data class GifUniqueAnalytics(
    @SerializedName("onload") val onload: GifUniqueAnalytic,
    @SerializedName("onclick") val onclick: GifUniqueAnalytic,
    @SerializedName("onsent") val onsent: GifUniqueAnalytic
)
data class GifUniqueAnalytic(
    @SerializedName("url") val url: String
)

data class GifUniqueUser(
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

data class GifUniqueImages(
    @SerializedName("fixed_width_still") val fixed_width_still: GifUniqueImage,
    @SerializedName("preview_gif") val preview_gif: GifUniqueImage,
    @SerializedName("fixed_height_downsampled") val fixed_height_downsampled: GifUniqueImage,
    @SerializedName("preview") val preview: GifUniqueImage,
    @SerializedName("fixed_height_small") val fixed_height_small: GifUniqueImage,
    @SerializedName("downsized") val downsized: GifUniqueImage,
    @SerializedName("fixed_width_downsampled") val fixed_width_downsampled: GifUniqueImage,
    @SerializedName("fixed_width") val fixed_width: GifUniqueImage,
    @SerializedName("downsized_still") val downsized_still: GifUniqueImage,
    @SerializedName("downsized_medium") val downsized_medium: GifUniqueImage,
    @SerializedName("original_mp4") val original_mp4: GifUniqueImage,
    @SerializedName("downsized_large") val downsized_large: GifUniqueImage,
    @SerializedName("preview_webp") val preview_webp: GifUniqueImage,
    @SerializedName("original") val original: GifUniqueImage,
    @SerializedName("original_still") val original_still: GifUniqueImage,
    @SerializedName("fixed_height_small_still") val fixed_height_small_still: GifUniqueImage,
    @SerializedName("fixed_width_small") val fixed_width_small: GifUniqueImage,
    @SerializedName("lopping") val lopping: GifUniqueImage,
    @SerializedName("downsized_small") val downsized_small: GifUniqueImage,
    @SerializedName("fixed_width_small_still") val fixed_width_small_still: GifUniqueImage,
    @SerializedName("fixed_height_still") val fixed_height_still: GifUniqueImage,
    @SerializedName("fixed_height") val fixed_height: GifUniqueImage
)

data class GifUniqueImage(
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

data class MetaUnique(
    @SerializedName("status") val status: Int,
    @SerializedName("msg") val msg: String,
    @SerializedName("response_id") val response_id: String
)