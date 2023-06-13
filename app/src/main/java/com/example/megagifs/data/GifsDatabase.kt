package com.example.megagifs.data

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
@Database(entities = [GifEntity::class], version = 1)
abstract class GifsDatabase: RoomDatabase() {
    abstract fun gifDao():GifDao
}