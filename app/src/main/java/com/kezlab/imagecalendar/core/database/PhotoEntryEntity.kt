package com.kezlab.imagecalendar.core.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "photo_entries",
    indices = [
        Index("localDate"),
        Index("createdAtMillis"),
        Index("emotionTagId"),
    ],
)
data class PhotoEntryEntity(
    @PrimaryKey val id: String,
    val localDate: String,
    val note: String,
    val emotionTagId: String?,
    val capturedAtMillis: Long?,
    val createdAtMillis: Long,
    val updatedAtMillis: Long,
)
