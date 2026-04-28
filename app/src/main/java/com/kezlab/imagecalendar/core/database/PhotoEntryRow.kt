package com.kezlab.imagecalendar.core.database

data class PhotoEntryRow(
    val id: String,
    val localDate: String,
    val originalRelativePath: String,
    val thumbnailRelativePath: String,
    val note: String,
    val emotionTagId: String?,
    val createdAtMillis: Long,
)
