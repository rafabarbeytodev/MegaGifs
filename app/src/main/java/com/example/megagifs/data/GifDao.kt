package com.example.megagifs.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

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
interface GifDao {

    //Todos los gifs
    @Query("SELECT * FROM Gifs")
    fun getGifs(): Flow <List<GifEntity>>

    //Un Gif concreto por id
    @Query("SELECT * FROM Gifs WHERE id = :id ")
    fun getGif(id:String): List<GifEntity>

    //Los gifs favoritos
    @Query("SELECT * FROM Gifs WHERE favorite = :isFavorite ")
    fun getGifsFavorite(isFavorite:Boolean): List<GifEntity>

    //Inserta un Gif
    @Insert
    suspend fun addGif(item: GifEntity)

}