package com.aireadevs.megagifs.screenimages.domain.trendingsUsesCase

import com.aireadevs.megagifs.screenimages.data.retrofit.GifsRepository
import javax.inject.Inject

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screen6.domain
 *
 * Created by Rafael Barbeyto Torrellas on 02/06/2023 at 7:38
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class TrendingGifsUseCase @Inject constructor(
    private val repository: GifsRepository
){
    suspend operator fun invoke() = repository.getTrendingGifs()
}