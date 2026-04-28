package com.kezlab.imagecalendar.core.repository

import android.content.Context
import androidx.room.Room
import com.kezlab.imagecalendar.core.database.ImageCalendarDatabase
import com.kezlab.imagecalendar.core.storage.LocalImageStore

object AppContainer {
    @Volatile private var repository: PhotoEntryRepository? = null

    fun photoEntryRepository(context: Context): PhotoEntryRepository {
        return repository ?: synchronized(this) {
            repository ?: createRepository(context.applicationContext).also {
                repository = it
            }
        }
    }

    private fun createRepository(context: Context): PhotoEntryRepository {
        val database = Room.databaseBuilder(
            context,
            ImageCalendarDatabase::class.java,
            "image-calendar.db",
        ).build()
        return PhotoEntryRepository(
            dao = database.photoEntryDao(),
            imageStore = LocalImageStore(context),
        )
    }
}
