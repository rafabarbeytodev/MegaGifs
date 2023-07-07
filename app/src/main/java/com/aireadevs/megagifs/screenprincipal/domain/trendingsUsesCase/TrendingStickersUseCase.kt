package com.aireadevs.megagifs.screenprincipal.domain.trendingsUsesCase

import com.aireadevs.megagifs.screenprincipal.data.network.GifsRepository
import javax.inject.Inject

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.principalscreen.domain
 *
 * Created by Rafael Barbeyto Torrellas on 05/06/2023 at 15:51
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class TrendingStickersUseCase @Inject constructor(
    private val repository: GifsRepository
) {
    suspend operator fun invoke() = repository.getTrendingStickers()
}