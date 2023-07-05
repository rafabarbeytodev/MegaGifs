package com.example.megagifs.core

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.model
 *
 * Created by Rafael Barbeyto Torrellas on 01/06/2023 at 11:54
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
sealed class Routes(val route: String) {

    object SplashScreen : Routes("splashscreen")
    object PrincipalScreen : Routes("principalScreen?type={type}&search={search}") {
        fun createRoute(type: Int, search: String = "") =
            "principalScreen?type=$type&search=$search"
    }

    object DetailsScreen :
        Routes("detailsScreen?type={type}&origin={origin}&url={url}&avatar={avatar}&displayName={displayName}&userName={userName}&verified={verified}&id={id}&stateFavorite={stateFavorite}") {
        fun createRoute(
            type: Int,
            origin: Int,
            url: String,
            avatar: String,
            displayName: String,
            userName: String,
            verified: Boolean,
            id: String,
            stateFavorite: Boolean
        ) =
            "detailsScreen?type=$type&origin=$origin&url=$url&avatar=$avatar&displayName=$displayName&userName=$userName&verified=$verified&id=$id&stateFavorite=$stateFavorite"
    }

    object FavoritesScreen :
        Routes("favoritesScreen?type={type}") {
        fun createRoute(
            type: Int
        ) = "favoritesScreen?type=$type"
    }
}
