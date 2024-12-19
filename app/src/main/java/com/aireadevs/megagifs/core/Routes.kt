package com.aireadevs.megagifs.core

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.model
 *
 * Created by Rafael Barbeyto Torrellas on 01/06/2023 at 11:54
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
sealed class Routes(val route: String) {

    data object ImagesScreen : Routes("imagesScreen?search={search}") {
        fun createRoute(search: String = "") =
            "imagesScreen?search=$search"
    }

    data object DetailsScreen :
        Routes("detailsScreen?type={type}&typeFav={typeFav}&url={url}&search={search}&avatar={avatar}&displayName={displayName}&userName={userName}&verified={verified}&id={id}&stateFavorite={stateFavorite}") {
        fun createRoute(
            type: Int,
            url: String,
            search:String,
            avatar: String,
            displayName: String,
            userName: String,
            verified: Boolean,
            id: String,
            stateFavorite: Boolean
        ) =
            "detailsScreen?type=$type&url=$url&search=$search&avatar=$avatar&displayName=$displayName&userName=$userName&verified=$verified&id=$id&stateFavorite=$stateFavorite"
    }

}
