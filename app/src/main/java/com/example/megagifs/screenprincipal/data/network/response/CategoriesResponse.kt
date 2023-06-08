package com.example.megagifs.screenprincipal.data.network.response

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.principalscreen.data.network.response
 *
 * Created by Rafael Barbeyto Torrellas on 04/06/2023 at 21:46
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
data class CategoriesResponse(
val name: String,
val name_encoded: String,
val subcategories: List<Subcategory>,
val gif: Gif
)

data class Subcategory(
    val name: String,
    val name_encoded: String
)

data class Gif(
    val type: String,
    val id: String,
    val url: String,
    val slug: String,
    val bitly_gif_url: String,
    val bitly_url: String,
    val embed_url: String,
    val username: String,
    val source: String,
    val title: String,
    val rating: String,
    val content_url: String,
    val source_tld: String,
    val source_post_url: String,
    val is_sticker: Int,
    val import_datetime: String,
    val trending_datetime: String,
    val create_datetime: String,
    val update_datetime: String,
    val images: Images
)

data class Images(
    val fixed_width_still: ImageInfo,
    val fixed_height_downsampled: ImageInfo,
    val preview_gif: ImageInfo,
    val preview: ImageInfo,
    val fixed_height_small: ImageInfo,
    val downsized: ImageInfo,
    val fixed_width_downsampled: ImageInfo,
    val fixed_width: ImageInfo,
    val downsized_still: ImageInfo,
    val downsized_medium: ImageInfo,
    val original_mp4: ImageInfo,
    val downsized_large: ImageInfo,
    val preview_webp: ImageInfo,
    val original: ImageInfo,
    val original_still: ImageInfo,
    val fixed_height_small_still: ImageInfo,
    val fixed_width_small: ImageInfo,
    val looping: Looping,
    val downsized_small: ImageInfo,
    val fixed_width_small_still: ImageInfo,
    val fixed_height_still: ImageInfo,
    val fixed_height: ImageInfo
)

data class ImageInfo(
    val height: String,
    val size: String,
    val url: String,
    val width: String,
    val webp: String? = null,
    val webp_size: String? = null,
    val mp4: String? = null,
    val mp4_size: String? = null
)

data class Looping(
    val mp4: String,
    val mp4_size: String
)
