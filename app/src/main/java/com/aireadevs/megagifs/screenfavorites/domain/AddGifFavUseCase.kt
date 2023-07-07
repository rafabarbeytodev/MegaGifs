package com.aireadevs.megagifs.screenfavorites.domain

import com.aireadevs.megagifs.screenfavorites.data.room.GifsFavRepository
import com.aireadevs.megagifs.screenfavorites.ui.model.FavModel
import javax.inject.Inject

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.screenfavorites.domain
 *
 * Created by Rafael Barbeyto Torrellas on 25/06/2023 at 15:04
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class AddGifFavUseCase  @Inject constructor(
    private val repository: GifsFavRepository
) {
    suspend operator fun invoke(favModel: FavModel) = repository.add(favModel)
}