package com.example.megagifs.model

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
    object Screen1 : Routes("screen1")
    object Screen2 : Routes("screen2")
    object PrincipalScreen : Routes("principalscreen")
    object Screen4 : Routes("screen4")
    object Screen5 : Routes("screen5")
    object Screen6 : Routes("screen6")
}
