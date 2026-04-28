# Final Notes: Tech Implementation Council

Date: 2026-04-28

## Locked Technical Decisions

- Platform: Android Native.
- Language/UI: Kotlin + Jetpack Compose.
- Module structure: single `:app` module for MVP.
- Package: `com.kezlab.imagecalendar`.
- Architecture: Single Activity + Compose UI + MVVM-ish screen state + local repositories.
- Metadata storage: Room.
- Lightweight preferences: DataStore later when onboarding/settings persistence is implemented.
- Core tables: `PhotoEntry` and `LocalAsset`; no `DayRecord` table in MVP.
- Date storage: `localDate` as `YYYY-MM-DD`.
- Image storage: app-specific internal storage copy.
- Asset path storage: relative paths.
- Thumbnail strategy: generate thumbnails at save time.
- Initial media input: Android Photo Picker.
- Camera: deferred from first vertical slice.
- Network: no `INTERNET` permission and no network dependency in MVP.

## First Implementation Slice

Implement `001 + 002` as one vertical slice:

- Android project scaffold.
- Compose design tokens and app shell.
- Bottom tabs: Calendar, Add, Archive, Settings.
- Calendar screen with today highlight, month navigation, local badge, and record markers.
- Add Record screen with gallery selection, date, optional memo, optional emotion tag, required-photo validation.
- Local save path with internal image copy, thumbnail generation, Room metadata insert, and rollback cleanup.
- Day Detail screen showing saved records for the selected local date.
- Save success navigates to Day Detail.
- Returning to Calendar shows the marker.

## Verification Gate

- Build succeeds.
- App opens without login.
- Calendar local badge is visible.
- Gallery image can be saved locally.
- Day Detail shows the saved record.
- Calendar marker reflects the saved record.
- Manifest has no `INTERNET` permission.
- UI contains no account, sync, upload, sharing, public feed, likes, comments, or follows.
