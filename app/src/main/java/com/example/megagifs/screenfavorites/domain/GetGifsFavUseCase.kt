package com.example.megagifs.screenfavorites.domain

import com.example.megagifs.screenfavorites.data.room.GifsFavRepository
import com.example.megagifs.screenfavorites.ui.model.FavModel
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

class GetGifsFavUseCase @Inject constructor(
    private val repository: GifsFavRepository
) {
    //operator fun invoke(): List<FavModel> = repository.gifsFav
    suspend operator fun invoke(): List<FavModel> = repository.onGetAllFavs()
}

