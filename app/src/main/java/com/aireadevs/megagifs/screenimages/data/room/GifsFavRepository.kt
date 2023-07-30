package com.aireadevs.megagifs.screenimages.data.room

import com.aireadevs.megagifs.screenimages.ui.model.FavModel
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
class GifsFavRepository @Inject constructor(
    private val gifsFavDao: GifsFavDao
) {
    suspend fun onGetAllFavs():  List<FavModel>{
        //mapeamos de la capa de datos a la de dominio/ui
        return gifsFavDao.getFavGifs().map { entity ->
            FavModel(
                entity.id_int,
                entity.id,
                entity.url,
                entity.type,
                entity.avatar_url,
                entity.display_name,
                entity.username,
                entity.is_verified
            )
        }
    }

    suspend fun add(favModel: FavModel) {
        gifsFavDao.addFavGif(
            GifsFavEntity(
                favModel.id_int,
                favModel.id,
                favModel.url,
                favModel.type,
                favModel.avatar_url,
                favModel.display_name,
                favModel.username,
                favModel.is_verified
            )
        )
    }
    suspend fun delete(favModel: FavModel) {
        gifsFavDao.deleteFavGif(
            GifsFavEntity(
                favModel.id_int,
                favModel.id,
                favModel.url,
                favModel.type,
                favModel.avatar_url,
                favModel.display_name,
                favModel.username,
                favModel.is_verified
            )
        )
    }
}
