package com.example.megagifs.core

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.model
 *
 * Created by Rafael Barbeyto Torrellas on 05/06/2023 at 16:03
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
sealed class Origins(val origin: Int) {

    object Gifs : Origins(0)
    object Emojis : Origins(1)
    object Stickers : Origins(2)
    object Favorites : Origins(3)
}
