package com.example.megagifs.principalscreen.domain

import com.example.megagifs.principalscreen.data.network.GifsRepository
import com.example.megagifs.principalscreen.data.network.response.GifsResponse

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.principalscreen.domain
 *
 * Created by Rafael Barbeyto Torrellas on 05/06/2023 at 15:51
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class TrendingEmojisUseCase {

    private val repository = GifsRepository()
    suspend operator fun invoke(): GifsResponse? {
        return repository.getTrendingEmojis()
    }
}