package com.kezlab.imagecalendar.core.model

data class PhotoEntry(
    val id: String,
    val localDate: String,
    val originalRelativePath: String,
    val thumbnailRelativePath: String,
    val note: String,
    val emotionTag: EmotionTag?,
    val createdAtMillis: Long,
)

enum class EmotionTag(val label: String) {
    Calm("차분"),
    Happy("기쁨"),
    Tired("피곤"),
    Cozy("포근"),
    Busy("바쁨"),
}
