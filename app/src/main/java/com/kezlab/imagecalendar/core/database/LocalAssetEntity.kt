package com.kezlab.imagecalendar.core.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "local_assets",
    foreignKeys = [
        ForeignKey(
            entity = PhotoEntryEntity::class,
            parentColumns = ["id"],
            childColumns = ["entryId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index("entryId")],
)
data class LocalAssetEntity(
    @PrimaryKey val id: String,
    val entryId: String,
    val originalRelativePath: String,
    val thumbnailRelativePath: String,
    val mimeType: String,
    val width: Int,
    val height: Int,
    val fileSizeBytes: Long,
    val createdAtMillis: Long,
)
