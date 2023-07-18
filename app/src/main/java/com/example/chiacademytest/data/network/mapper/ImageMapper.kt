package com.example.chiacademytest.data.network.mapper

import com.example.chiacademytest.data.network.entity.NetworkImage
import com.example.chiacademytest.entity.Image

internal fun NetworkImage.toImage(): Image {
    return Image(
        id = this.id,
        url = this.url,
        isFavorite = false
    )
}
