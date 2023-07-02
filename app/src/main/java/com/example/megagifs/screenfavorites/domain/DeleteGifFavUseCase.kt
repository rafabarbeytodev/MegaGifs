package com.example.megagifs.screenfavorites.domain

import com.example.megagifs.screenfavorites.data.room.GifsFavRepository
import com.example.megagifs.screenfavorites.ui.model.FavModel
import javax.inject.Inject

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screenfavorites.domain
 *
 * Created by Rafael Barbeyto Torrellas on 26/06/2023 at 16:03
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class DeleteGifFavUseCase @Inject constructor(
    private val repository: GifsFavRepository
) {
    suspend operator fun invoke(favModel: FavModel) = repository.delete(favModel)
}