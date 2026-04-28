package com.kezlab.imagecalendar.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        PhotoEntryEntity::class,
        LocalAssetEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class ImageCalendarDatabase : RoomDatabase() {
    abstract fun photoEntryDao(): PhotoEntryDao
}
