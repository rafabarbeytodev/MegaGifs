package com.aireadevs.megagifs.screenprincipal.ui.model

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
    @SerializedName("avatar_url") val avatar_url: String = "",
    @SerializedName("username") val username: String = "",
    @SerializedName("display_name") val display_name: String = "",
    @SerializedName("is_verified") val is_verified: Boolean = true
)

data class GifImages(
    @SerializedName("fixed_height") val fixed_height: GifImage,
    @SerializedName("fixed_width") val fixed_width: GifImage
)

data class GifImage(
    @SerializedName("height") val height: String = "",
    @SerializedName("width") val width: String = "",
    @SerializedName("size") val size: String = "",
    @SerializedName("url") val url: String = ""
)
