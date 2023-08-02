package com.aireadevs.megagifs.screenimages.ui.model

import com.google.gson.annotations.SerializedName

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.screenprincipal.ui.model
 *
 * Created by Rafael Barbeyto Torrellas on 12/06/2023 at 14:03
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
data class GifsModel(
    @SerializedName("data") val data: List<GifItem>
)

data class GifItem(
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: GifImages,
    @SerializedName("user") val user: GifUser? = null
)


data class GifUser(
    @SerializedName("avatar_url") val avatarUrl: String = "",
    @SerializedName("username") val username: String = "",
    @SerializedName("display_name") val displayName: String = "",
    @SerializedName("is_verified") val isVerified: Boolean = true
)

data class GifImages(
    @SerializedName("fixed_height") val fixedHeight: GifImage,
    @SerializedName("fixed_width") val fixedWidth: GifImage,
    @SerializedName("downsized_large") val downsizedLarge: GifImage,
    @SerializedName("downsized_medium") val downsizedMedium: GifImage
)

data class GifImage(
    @SerializedName("height") val height: String = "",
    @SerializedName("width") val width: String = "",
    @SerializedName("size") val size: String = "",
    @SerializedName("url") val url: String = ""
)
