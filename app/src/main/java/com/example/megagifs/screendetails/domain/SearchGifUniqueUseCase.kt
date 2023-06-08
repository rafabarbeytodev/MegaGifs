package com.example.megagifs.screendetails.domain

import com.example.megagifs.screendetails.data.network.GifUniqueRepository
import com.example.megagifs.screendetails.data.network.response.GifUniqueResponse
import javax.inject.Inject

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.principalscreen.domain
 *
 * Created by Rafael Barbeyto Torrellas on 05/06/2023 at 20:16
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class SearchGifUniqueUseCase @Inject constructor(
    private val repository: GifUniqueRepository
) {
    suspend operator fun invoke(id:String): GifUniqueResponse? {
        return repository.getGif(id)
    }

}