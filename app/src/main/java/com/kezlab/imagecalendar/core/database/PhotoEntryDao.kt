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
        ORDER BY entries.localDate DESC, entries.createdAtMillis DESC
        """,
    )
    abstract suspend fun getAllEntryRows(): List<PhotoEntryRow>

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
        WHERE entries.id = :entryId
        LIMIT 1
        """,
    )
    abstract suspend fun getEntryRow(entryId: String): PhotoEntryRow?

    @Query("SELECT * FROM local_assets WHERE entryId = :entryId LIMIT 1")
    abstract suspend fun getAssetForEntry(entryId: String): LocalAssetEntity?

    @Query(
        """
        UPDATE photo_entries
        SET localDate = :localDate,
            note = :note,
            emotionTagId = :emotionTagId,
            updatedAtMillis = :updatedAtMillis
        WHERE id = :entryId
        """,
    )
    abstract suspend fun updateEntry(
        entryId: String,
        localDate: String,
        note: String,
        emotionTagId: String?,
        updatedAtMillis: Long,
    ): Int

    @Query("DELETE FROM photo_entries WHERE id = :entryId")
    abstract suspend fun deleteEntry(entryId: String): Int

    @Query("DELETE FROM photo_entries")
    abstract suspend fun deleteAllEntries(): Int

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
