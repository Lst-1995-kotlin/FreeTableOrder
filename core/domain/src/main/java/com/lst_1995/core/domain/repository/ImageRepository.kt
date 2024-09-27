package com.lst_1995.core.domain.repository

import java.awt.Image

interface ImageRepository {
    suspend fun downloadImage(url: String): Image

    suspend fun uploadImage(url: String)
}
