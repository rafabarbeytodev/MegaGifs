package com.aireadevs.megagifs.data.datastore

import com.google.gson.annotations.SerializedName
import java.util.*

/*****
 * Proyect: sms2mailphone
 * Package: com.aireadevs.android.kotlin.datastore.data
 *
 * Created by Rafael Barbeyto Torrellas on 27/09/2022 at 9:23
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2022.
 *****/
data class DataStoreEntity(
    @SerializedName("mailDeveloper") var mailDeveloper: String = ""
)
