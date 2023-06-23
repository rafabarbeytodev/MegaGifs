package com.example.megagifs.screenprincipal.domain.trendingsUsesCase

import com.example.megagifs.screenprincipal.data.network.GifsRepository
import com.example.megagifs.screenprincipal.data.network.entity.GifsEntity
import com.example.megagifs.screenprincipal.ui.model.GifsModel
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
    suspend operator fun invoke(): GifsModel? = repository.getTrendingStickers()
}