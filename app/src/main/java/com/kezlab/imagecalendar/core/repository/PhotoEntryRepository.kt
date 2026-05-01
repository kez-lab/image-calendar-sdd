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

data class UpdatePhotoEntryInput(
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

    suspend fun updatePhotoEntry(entryId: String, input: UpdatePhotoEntryInput): PhotoEntry {
        val updated = dao.updateEntry(
            entryId = entryId,
            localDate = input.localDate,
            note = input.note.trim(),
            emotionTagId = input.emotionTag?.name,
            updatedAtMillis = System.currentTimeMillis(),
        )
        check(updated == 1) { "Photo entry not found: $entryId" }
        return requireNotNull(dao.getEntryRow(entryId)) {
            "Photo entry missing after update: $entryId"
        }.toDomain()
    }

    suspend fun deletePhotoEntry(entryId: String) {
        val asset = requireNotNull(dao.getAssetForEntry(entryId)) {
            "Local asset not found for entry: $entryId"
        }
        val deleted = dao.deleteEntry(entryId)
        check(deleted == 1) { "Photo entry not found: $entryId" }
        imageStore.deleteEntryDirectory(asset.originalRelativePath)
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
