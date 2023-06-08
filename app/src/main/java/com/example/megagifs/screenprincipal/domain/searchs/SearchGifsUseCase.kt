package com.example.megagifs.screenprincipal.domain.searchs

import com.example.megagifs.screenprincipal.data.network.GifsRepository
import com.example.megagifs.screenprincipal.data.network.response.GifsResponse
import javax.inject.Inject

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.principalscreen.domain
 *
 * Created by Rafael Barbeyto Torrellas on 05/06/2023 at 20:16
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class SearchGifsUseCase @Inject constructor(
    private val repository: GifsRepository
) {
     suspend operator fun invoke(search:String): GifsResponse? {
        return repository.getSearchGifs(search)
    }
}