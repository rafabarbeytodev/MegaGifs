package com.aireadevs.megagifs.screenfavorites.data.room

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
    tableName = "GifsFav",
    indices = [Index(value = ["id_int"], unique = true)]
)

data class GifsFavEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id_int") val id_int: Long = 0,
    @SerializedName("id") val id: String = "",
    @SerializedName("url") val url: String,
    @SerializedName("type") val type: Int,
    @SerializedName("avatar_url") val avatar_url: String,
    @SerializedName("display_name") val display_name: String,
    @SerializedName("username") val username: String,
    @SerializedName("is_verified") val is_verified: Boolean,
)
