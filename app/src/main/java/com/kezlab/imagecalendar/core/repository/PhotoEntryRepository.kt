package com.kezlab.imagecalendar.core.repository

import android.net.Uri
import com.kezlab.imagecalendar.core.database.LocalAssetEntity
import com.kezlab.imagecalendar.core.database.PhotoEntryDao
import com.kezlab.imagecalendar.core.database.PhotoEntryEntity
import com.kezlab.imagecalendar.core.database.PhotoEntryRow
import com.kezlab.imagecalendar.core.model.EmotionTag
import com.kezlab.imagecalendar.core.model.PhotoEntry
import com.kezlab.imagecalendar.core.storage.LocalImageStore
import com.kezlab.imagecalendar.core.storage.StoredImage
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class CreatePhotoEntryInput(
    val localDate: String,
    val note: String,
    val emotionTag: EmotionTag?,
)

class PhotoEntryRepository(
    private val dao: PhotoEntryDao,
    private val imageStore: LocalImageStore,
) {
    fun observeEntries(): Flow<List<PhotoEntry>> = dao.observeEntries().map { rows ->
        rows.map { it.toDomain() }
    }

    suspend fun createPhotoEntryFromUri(uri: Uri, input: CreatePhotoEntryInput): PhotoEntry {
        val entryId = UUID.randomUUID().toString()
        val stored = imageStore.copyFromUri(uri, entryId)
        return insertStoredEntry(entryId, stored, input)
    }

    suspend fun createDebugFixtureEntry(input: CreatePhotoEntryInput): PhotoEntry {
        val entryId = UUID.randomUUID().toString()
        val stored = imageStore.createDebugFixture(entryId)
        return insertStoredEntry(entryId, stored, input)
    }

    private suspend fun insertStoredEntry(
        entryId: String,
        stored: StoredImage,
        input: CreatePhotoEntryInput,
    ): PhotoEntry {
        val now = System.currentTimeMillis()
        val entry = PhotoEntryEntity(
            id = entryId,
            localDate = input.localDate,
            note = input.note.trim(),
            emotionTagId = input.emotionTag?.name,
            capturedAtMillis = null,
            createdAtMillis = now,
            updatedAtMillis = now,
        )
        val asset = LocalAssetEntity(
            id = UUID.randomUUID().toString(),
            entryId = entryId,
            originalRelativePath = stored.originalRelativePath,
            thumbnailRelativePath = stored.thumbnailRelativePath,
            mimeType = stored.mimeType,
            width = stored.width,
            height = stored.height,
            fileSizeBytes = stored.fileSizeBytes,
            createdAtMillis = now,
        )

        return try {
            dao.insertEntryWithAsset(entry, asset)
            PhotoEntry(
                id = entry.id,
                localDate = entry.localDate,
                originalRelativePath = stored.originalRelativePath,
                thumbnailRelativePath = stored.thumbnailRelativePath,
                note = entry.note,
                emotionTag = input.emotionTag,
                createdAtMillis = entry.createdAtMillis,
            )
        } catch (throwable: Throwable) {
            imageStore.deleteStoredImage(stored)
            throw throwable
        }
    }

    private fun PhotoEntryRow.toDomain(): PhotoEntry = PhotoEntry(
        id = id,
        localDate = localDate,
        originalRelativePath = originalRelativePath,
        thumbnailRelativePath = thumbnailRelativePath,
        note = note,
        emotionTag = EmotionTag.fromId(emotionTagId),
        createdAtMillis = createdAtMillis,
    )
}
