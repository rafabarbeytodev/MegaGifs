package com.example.megagifs.model

import androidx.annotation.DrawableRes

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.model
 *
 * Created by Rafael Barbeyto Torrellas on 01/06/2023 at 9:51
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
data class SuperGifs(
    var supergiftitle: String,
    var username: String,
    var displayname: String,
    @DrawableRes var gif: Int
)
