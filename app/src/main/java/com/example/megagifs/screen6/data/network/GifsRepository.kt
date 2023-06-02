package com.example.megagifs.screen6.data.network

import android.util.Log
import com.example.megagifs.screen6.data.network.response.GifsResponse
import com.example.megagifs.screen6.data.network.response.GiphyItem

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screen6.data.network
 *
 * Created by Rafael Barbeyto Torrellas on 02/06/2023 at 7:28
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class GifsRepository {
    private val api = GifsService()

    suspend fun getGifs(): GifsResponse? {
        return api.getGifs()
    }
}