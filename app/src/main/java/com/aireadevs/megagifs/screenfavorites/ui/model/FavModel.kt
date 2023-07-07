package com.aireadevs.megagifs.screenfavorites.ui.model

import com.google.gson.annotations.SerializedName

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screenfavorites.ui.model
 *
 * Created by Rafael Barbeyto Torrellas on 25/06/2023 at 14:38
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
data class FavModel(
    @SerializedName("id_int") val id_int: Long = 0,
    @SerializedName("id") val id: String = "",
    @SerializedName("url") val url: String,
    @SerializedName("type") val type: Int,
    @SerializedName("avatar_url") val avatar_url: String,
    @SerializedName("display_name") val display_name: String,
    @SerializedName("username") val username: String,
    @SerializedName("is_verified") val is_verified: Boolean,
)
