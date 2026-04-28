package com.kezlab.imagecalendar.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PhotoEntryDao {
    @Query(
        """
        SELECT
            entries.id AS id,
            entries.localDate AS localDate,
            assets.originalRelativePath AS originalRelativePath,
            assets.thumbnailRelativePath AS thumbnailRelativePath,
            entries.note AS note,
            entries.emotionTagId AS emotionTagId,
            entries.createdAtMillis AS createdAtMillis
        FROM photo_entries AS entries
        INNER JOIN local_assets AS assets ON assets.entryId = entries.id
        ORDER BY entries.createdAtMillis ASC
        """,
    )
    abstract fun observeEntries(): Flow<List<PhotoEntryRow>>

    @Insert
    protected abstract suspend fun insertEntry(entry: PhotoEntryEntity)

    @Insert
    protected abstract suspend fun insertAsset(asset: LocalAssetEntity)

    @Transaction
    open suspend fun insertEntryWithAsset(entry: PhotoEntryEntity, asset: LocalAssetEntity) {
        insertEntry(entry)
        insertAsset(asset)
    }
}
