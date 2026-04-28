package com.kezlab.imagecalendar.core.storage

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

data class StoredImage(
    val originalRelativePath: String,
    val thumbnailRelativePath: String,
)

class LocalImageStore(private val context: Context) {
    fun copyFromUri(uri: Uri): StoredImage {
        val entryId = UUID.randomUUID().toString()
        val entryDir = File(context.filesDir, "entries/$entryId")
        val originalFile = File(entryDir, "original.jpg")
        val thumbnailFile = File(entryDir, "thumb.jpg")

        try {
            entryDir.mkdirs()
            copyUriToFile(context.contentResolver, uri, originalFile)
            createThumbnail(originalFile, thumbnailFile)
            return StoredImage(
                originalRelativePath = "entries/$entryId/original.jpg",
                thumbnailRelativePath = "entries/$entryId/thumb.jpg",
            )
        } catch (throwable: Throwable) {
            entryDir.deleteRecursively()
            throw throwable
        }
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
