package com.aireadevs.megagifs.core

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.model
 *
 * Created by Rafael Barbeyto Torrellas on 05/06/2023 at 16:03
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
sealed class Types(val type: Int) {

    object Gifs : Types(0)
    object Stickers : Types(1)
    object Emojis : Types(2)
    object Favorites : Types(3)
    object SearchGifs : Types(4)
    object SearchStickers : Types(5)

    object DialogDeveloper: Types(6)
    object DialogVersionInfo: Types(7)

}
