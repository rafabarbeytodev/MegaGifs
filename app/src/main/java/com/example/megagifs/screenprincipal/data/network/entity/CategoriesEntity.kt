package com.example.megagifs.screenprincipal.data.network.entity

import com.google.gson.annotations.SerializedName

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.principalscreen.data.network.response
 *
 * Created by Rafael Barbeyto Torrellas on 04/06/2023 at 21:46
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
data class CategoriesEntity(
    @SerializedName("name") val name: String,
    @SerializedName("name_encoded") val name_encoded: String,
    @SerializedName("subcategories") val subcategories: List<Subcategory>,
    @SerializedName("gif") val gif: Gif
)

data class Subcategory(
    @SerializedName("name") val name: String,
    @SerializedName("name_encoded") val name_encoded: String
)

data class Gif(
    @SerializedName("type") val type: String,
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("username") val username: String,
    @SerializedName("source") val source: String,
    @SerializedName("title") val title: String,
    @SerializedName("import_datetime") val import_datetime: String,
    @SerializedName("trending_datetime") val trending_datetime: String,
    @SerializedName("create_datetime") val create_datetime: String,
    @SerializedName("update_datetime") val update_datetime: String,
    @SerializedName("images") val images: Images
)

data class Images(
    @SerializedName("fixed_width_still") val fixed_width_still: ImageInfo,
    @SerializedName("fixed_height_downsampled") val fixed_height_downsampled: ImageInfo,
    @SerializedName("preview_gif") val preview_gif: ImageInfo,
    @SerializedName("preview") val preview: ImageInfo,
    @SerializedName("fixed_height_small") val fixed_height_small: ImageInfo,
    @SerializedName("downsized") val downsized: ImageInfo,
    @SerializedName("fixed_width_downsampled") val fixed_width_downsampled: ImageInfo,
    @SerializedName("fixed_width") val fixed_width: ImageInfo,
    @SerializedName("downsized_still") val downsized_still: ImageInfo,
    @SerializedName("downsized_medium") val downsized_medium: ImageInfo,
    @SerializedName("original_mp4") val original_mp4: ImageInfo,
    @SerializedName("downsized_large") val downsized_large: ImageInfo,
    @SerializedName("preview_webp") val preview_webp: ImageInfo,
    @SerializedName("original") val original: ImageInfo,
    @SerializedName("original_still") val original_still: ImageInfo,
    @SerializedName("fixed_height_small_still") val fixed_height_small_still: ImageInfo,
    @SerializedName("fixed_width_small") val fixed_width_small: ImageInfo,
    @SerializedName("downsized_small") val downsized_small: ImageInfo,
    @SerializedName("fixed_width_small_still") val fixed_width_small_still: ImageInfo,
    @SerializedName("fixed_height_still") val fixed_height_still: ImageInfo,
    @SerializedName("fixed_height") val fixed_height: ImageInfo
)

data class ImageInfo(
    @SerializedName("height") val height: String,
    @SerializedName("size") val size: String,
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: String,
    @SerializedName("webp") val webp: String? = null,
    @SerializedName("webp_size") val webp_size: String? = null,
    @SerializedName("mp4") val mp4: String? = null,
    @SerializedName("mp4_size") val mp4_size: String? = null
)
