package com.aireadevs.megagifs.screenimages.ui.model

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
    @SerializedName("idInt") val idInt: Long = 0,
    @SerializedName("id") val id: String = "",
    @SerializedName("url") val url: String,
    @SerializedName("type") val type: Int,
    @SerializedName("avatarUrl") val avatarUrl: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("username") val username: String,
    @SerializedName("isVerified") val isVerified: Boolean,
)
