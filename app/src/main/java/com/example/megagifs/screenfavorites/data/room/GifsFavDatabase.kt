package com.example.megagifs.screenfavorites.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.data
 *
 * Created by Rafael Barbeyto Torrellas on 10/06/2023 at 16:14
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Database(entities = [GifsFavEntity::class], version = 2)
abstract class GifsFavDatabase: RoomDatabase() {
    abstract fun gifFavDao(): GifsFavDao
}
