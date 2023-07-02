package com.example.megagifs.screenprincipal.data.network

import com.example.megagifs.screenprincipal.data.network.entity.GifsEntity
import com.example.megagifs.screenprincipal.ui.model.GifImage
import com.example.megagifs.screenprincipal.ui.model.GifImages
import com.example.megagifs.screenprincipal.ui.model.GifUser
import com.example.megagifs.screenprincipal.ui.model.GifsModel
import com.example.megagifs.screenprincipal.data.network.entity.GifItem
import javax.inject.Inject

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screen6.data.network
 *
 * Created by Rafael Barbeyto Torrellas on 02/06/2023 at 7:28
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

class GifsRepository @Inject constructor(
    private val api: GifsService
) {

    //mapeamos el Entity recibido de la capa de datos al modelo de la capa ui
    suspend fun getTrendingGifs(): GifsModel? {
        val gifsEntity = api.getTrendingGifs()
        return gifsEntity?.mapToGifsModel()
    }
    suspend fun getTrendingStickers(): GifsModel? {
        val gifsEntity = api.getTrendingStickers()
        return gifsEntity?.mapToGifsModel()
    }
    suspend fun getTrendingEmojis(): GifsModel? {
        val gifsEntity = api.getTrendingEmojis()
        return gifsEntity?.mapToGifsModel()
    }
    suspend fun getSearchGifs(search: String): GifsModel? {
        val gifsEntity = api.getSearchGifs(search)
        return gifsEntity?.mapToGifsModel()
    }
    suspend fun getSearchStickers(search: String): GifsModel? {
        val gifsEntity = api.getSearchStickers(search)
        return gifsEntity?.mapToGifsModel()
    }
    private fun GifsEntity.mapToGifsModel(): GifsModel {
        val gifItems = data.map { gifItemEntity ->
            gifItemEntity.mapToGifItem()
        }
        return GifsModel(data = gifItems)
    }
    private fun GifItem.mapToGifItem(): com.example.megagifs.screenprincipal.ui.model.GifItem {
        return com.example.megagifs.screenprincipal.ui.model.GifItem(
            id = id,
            images = images.mapToGifImages(),
            user = user?.mapToGifUser()
        )
    }
    private fun com.example.megagifs.screenprincipal.data.network.entity.GifImages.mapToGifImages(): GifImages {
        return GifImages(
            fixed_height = fixed_height.mapToGifImage(),
            fixed_width = fixed_width.mapToGifImage()
        )
    }
    private fun com.example.megagifs.screenprincipal.data.network.entity.GifImage.mapToGifImage(): GifImage {
        return GifImage(
            height = height,
            width = width,
            size = size,
            url = url
        )
    }
    private fun com.example.megagifs.screenprincipal.data.network.entity.GifUser.mapToGifUser(): GifUser {
        return GifUser(
            avatar_url = avatar_url,
            username = username,
            display_name = display_name,
            is_verified = is_verified
        )
    }
}