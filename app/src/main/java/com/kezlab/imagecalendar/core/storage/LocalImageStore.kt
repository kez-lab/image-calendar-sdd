package com.kezlab.imagecalendar.core.storage

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.kezlab.imagecalendar.core.model.PhotoEntry
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

data class StoredImage(
    val originalRelativePath: String,
    val thumbnailRelativePath: String,
    val mimeType: String,
    val width: Int,
    val height: Int,
    val fileSizeBytes: Long,
)

class LocalImageStore(private val context: Context) {
    fun copyFromUri(uri: Uri, entryId: String): StoredImage {
        val entryDir = File(context.filesDir, "entries/$entryId")
        val originalFile = File(entryDir, "original.jpg")
        val thumbnailFile = File(entryDir, "thumb.jpg")

        try {
            entryDir.mkdirs()
            copyUriToFile(context.contentResolver, uri, originalFile)
            createThumbnail(originalFile, thumbnailFile)
            return storedImage(entryId, originalFile)
        } catch (throwable: Throwable) {
            entryDir.deleteRecursively()
            throw throwable
        }
    }

    fun createDebugFixture(entryId: String): StoredImage {
        val entryDir = File(context.filesDir, "entries/$entryId")
        val originalFile = File(entryDir, "original.jpg")
        val thumbnailFile = File(entryDir, "thumb.jpg")

        try {
            entryDir.mkdirs()
            val bitmap = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888)
            bitmap.eraseColor(0xFFEAE7F8.toInt())
            FileOutputStream(originalFile).use { output ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, output)
            }
            bitmap.recycle()
            createThumbnail(originalFile, thumbnailFile)
            return storedImage(entryId, originalFile)
        } catch (throwable: Throwable) {
            entryDir.deleteRecursively()
            throw throwable
        }
    }

    fun deleteStoredImage(storedImage: StoredImage): Boolean {
        return deleteEntryDirectory(storedImage.originalRelativePath)
    }

    fun deleteEntryDirectory(originalRelativePath: String): Boolean {
        val entryDir = File(context.filesDir, originalRelativePath).parentFile ?: return true
        return !entryDir.exists() || entryDir.deleteRecursively()
    }

    fun deleteAllEntryDirectories(): Boolean {
        val entriesDir = File(context.filesDir, "entries")
        return !entriesDir.exists() || entriesDir.deleteRecursively()
    }

    fun exportBackup(destination: Uri, entries: List<PhotoEntry>): Int {
        context.contentResolver.openOutputStream(destination).use { output ->
            requireNotNull(output) { "Unable to open backup destination." }
            ZipOutputStream(output).use { zip ->
                zip.putNextEntry(ZipEntry("manifest.json"))
                zip.write(buildBackupManifest(entries).toByteArray(Charsets.UTF_8))
                zip.closeEntry()

                entries.forEach { entry ->
                    zipFileIfExists(zip, entry.originalRelativePath, "assets/${entry.id}/original.jpg")
                    zipFileIfExists(zip, entry.thumbnailRelativePath, "assets/${entry.id}/thumb.jpg")
                }
            }
        }
        return entries.size
    }

    private fun copyUriToFile(contentResolver: ContentResolver, uri: Uri, destination: File) {
        contentResolver.openInputStream(uri).use { input ->
            requireNotNull(input) { "Unable to open selected image." }
            FileOutputStream(destination).use { output ->
                input.copyTo(output)
            }
        }
    }

    private fun createThumbnail(originalFile: File, thumbnailFile: File) {
        val bitmap = BitmapFactory.decodeFile(originalFile.absolutePath)
            ?: error("Unable to decode selected image.")
        val thumbnail = bitmap.centerCropToSquare(512)
        FileOutputStream(thumbnailFile).use { output ->
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 82, output)
        }
        if (thumbnail !== bitmap) {
            thumbnail.recycle()
        }
        bitmap.recycle()
    }

    private fun storedImage(entryId: String, originalFile: File): StoredImage {
        val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        BitmapFactory.decodeFile(originalFile.absolutePath, options)
        return StoredImage(
            originalRelativePath = "entries/$entryId/original.jpg",
            thumbnailRelativePath = "entries/$entryId/thumb.jpg",
            mimeType = options.outMimeType ?: "image/jpeg",
            width = options.outWidth,
            height = options.outHeight,
            fileSizeBytes = originalFile.length(),
        )
    }

    private fun zipFileIfExists(zip: ZipOutputStream, relativePath: String, entryName: String) {
        val file = File(context.filesDir, relativePath)
        if (!file.exists()) return

        zip.putNextEntry(ZipEntry(entryName))
        file.inputStream().use { input ->
            input.copyTo(zip)
        }
        zip.closeEntry()
    }

    private fun buildBackupManifest(entries: List<PhotoEntry>): String {
        val records = entries.joinToString(separator = ",\n") { entry ->
            """
            {
              "id": "${entry.id.jsonEscaped()}",
              "localDate": "${entry.localDate.jsonEscaped()}",
              "note": "${entry.note.jsonEscaped()}",
              "emotionTagId": ${entry.emotionTag?.name?.let { "\"${it.jsonEscaped()}\"" } ?: "null"},
              "createdAtMillis": ${entry.createdAtMillis},
              "originalAsset": "assets/${entry.id.jsonEscaped()}/original.jpg",
              "thumbnailAsset": "assets/${entry.id.jsonEscaped()}/thumb.jpg"
            }
            """.trimIndent()
        }

        return """
        {
          "schemaVersion": 1,
          "app": "Image Calendar",
          "recordCount": ${entries.size},
          "records": [
        $records
          ]
        }
        """.trimIndent()
    }

    private fun String.jsonEscaped(): String = buildString {
        this@jsonEscaped.forEach { char ->
            when (char) {
                '\\' -> append("\\\\")
                '"' -> append("\\\"")
                '\n' -> append("\\n")
                '\r' -> append("\\r")
                '\t' -> append("\\t")
                else -> append(char)
            }
        }
    }

    private fun Bitmap.centerCropToSquare(size: Int): Bitmap {
        val side = minOf(width, height)
        val left = (width - side) / 2
        val top = (height - side) / 2
        val cropped = Bitmap.createBitmap(this, left, top, side, side)
        return Bitmap.createScaledBitmap(cropped, size, size, true).also {
            if (cropped !== this) {
                cropped.recycle()
            }
        }
    }
}
