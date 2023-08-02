package com.aireadevs.megagifs.screenimages.data.retrofit

import com.aireadevs.megagifs.screenimages.data.retrofit.entity.GifsEntity
import com.aireadevs.megagifs.screenimages.ui.model.GifImage
import com.aireadevs.megagifs.screenimages.ui.model.GifImages
import com.aireadevs.megagifs.screenimages.ui.model.GifUser
import com.aireadevs.megagifs.screenimages.ui.model.GifsModel
import com.aireadevs.megagifs.screenimages.data.retrofit.entity.GifItem
import javax.inject.Inject

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.screen6.data.network
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
    private fun GifItem.mapToGifItem(): com.aireadevs.megagifs.screenimages.ui.model.GifItem {
        return com.aireadevs.megagifs.screenimages.ui.model.GifItem(
            id = id,
            images = images.mapToGifImages(),
            user = user?.mapToGifUser()
        )
    }
    private fun com.aireadevs.megagifs.screenimages.data.retrofit.entity.GifImages.mapToGifImages(): GifImages {
        return GifImages(
            fixedHeight = fixedHeight.mapToGifImage(),
            fixedWidth = fixedWidth.mapToGifImage(),
            downsizedLarge = downsizedLarge.mapToGifImage(),
            downsizedMedium = downsizedMedium.mapToGifImage()
        )
    }
    private fun com.aireadevs.megagifs.screenimages.data.retrofit.entity.GifImage.mapToGifImage(): GifImage {
        return GifImage(
            height = height,
            width = width,
            size = size,
            url = url
        )
    }
    private fun com.aireadevs.megagifs.screenimages.data.retrofit.entity.GifUser.mapToGifUser(): GifUser {
        return GifUser(
            avatarUrl = avatarUrl,
            username = username,
            displayName = displayName,
            isVerified = isVerified
        )
    }
}