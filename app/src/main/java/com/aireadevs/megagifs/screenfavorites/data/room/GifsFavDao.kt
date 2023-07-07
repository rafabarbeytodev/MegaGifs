package com.aireadevs.megagifs.screenfavorites.data.room

import androidx.room.*

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.data
 *
 * Created by Rafael Barbeyto Torrellas on 10/06/2023 at 16:32
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Dao
interface GifsFavDao {

    //Todos los gifs
    @Query("SELECT * FROM GifsFav")
    suspend fun getFavGifs(): List<GifsFavEntity>

    //Inserta un Gif en Favoritos
    @Insert
    suspend fun addFavGif(item: GifsFavEntity)

    //Elimina un Gif de Favoritos
    @Delete
    suspend fun deleteFavGif(item: GifsFavEntity)

}