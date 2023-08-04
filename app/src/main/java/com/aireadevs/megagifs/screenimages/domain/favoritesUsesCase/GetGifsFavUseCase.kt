package com.aireadevs.megagifs.screenimages.domain.favoritesUsesCase

import com.aireadevs.megagifs.screenimages.data.room.GifsFavRepository
import com.aireadevs.megagifs.screenimages.ui.model.FavModel
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
    suspend operator fun invoke(): List<FavModel> = repository.onGetAllFavs()
}

