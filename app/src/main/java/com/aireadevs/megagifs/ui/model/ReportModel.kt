package com.aireadevs.megagifs.ui.model

import com.google.gson.annotations.SerializedName

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.ui.model
 *
 * Created by Rafael Barbeyto Torrellas on 31/07/2023 at 13:28
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
data class Report(
    @SerializedName("appVersion") var appVersion: String,
    @SerializedName("model") var model: String,
    @SerializedName("versionOs") var versionOs: String,
    @SerializedName("read_media") var readMediaPermission: Boolean,
    @SerializedName("read_external") var readExternalPermission: Boolean,
    @SerializedName("write_external") var writeExternalPermission: Boolean
)

