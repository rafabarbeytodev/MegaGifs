package com.example.megagifs.data

import javax.inject.Inject
import javax.inject.Singleton

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.data
 *
 * Created by Rafael Barbeyto Torrellas on 10/06/2023 at 17:33
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Singleton
class GifRepository @Inject constructor(
    private val gifDao: GifDao) {
}