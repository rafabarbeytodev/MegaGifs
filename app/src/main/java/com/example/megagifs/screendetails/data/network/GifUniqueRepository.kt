package com.example.megagifs.screendetails.data.network

import com.example.megagifs.screendetails.data.network.entity.GifUniqueEntity
import javax.inject.Inject

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screen6.data.network
 *
 * Created by Rafael Barbeyto Torrellas on 02/06/2023 at 7:28
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class GifUniqueRepository @Inject constructor(
    private val api: GifUniqueService
) {
    suspend fun getGif(id: String): GifUniqueEntity? {
        return api.getGif(id)
    }

}