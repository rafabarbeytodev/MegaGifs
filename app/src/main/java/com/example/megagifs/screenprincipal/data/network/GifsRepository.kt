package com.example.megagifs.screenprincipal.data.network

import com.example.megagifs.screenprincipal.data.network.response.GifsResponse
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

class GifsRepository @Inject constructor(
    private val api: GifsService
) {
    suspend fun getTrendingGifs(): GifsResponse? {
        return api.getTrendingGifs()
    }
    suspend fun getTrendingStickers(): GifsResponse? {
        return api.getTrendingStickers()
    }
    suspend fun getTrendingEmojis(): GifsResponse? {
        return api.getTrendingEmojis()
    }
    suspend fun getSearchGifs(search: String): GifsResponse? {
        return api.getSearchGifs(search)
    }
    suspend fun getSearchStickers(search: String): GifsResponse? {
        return api.getSearchStickers(search)
    }

}