# Round 1 Opinions: Tech Implementation Council

Date: 2026-04-28

## CTO

- Use Android Native Kotlin + Jetpack Compose.
- Use Room for metadata.
- Copy images into app-specific internal storage.
- Generate thumbnails at save time.
- Start with a `001 + 002` vertical slice: app shell, Calendar marker, Add Record, local save, Day Detail confirmation.
- Defer Camera, Archive, Settings, and Backup until the first save/read loop is stable.

## Android Lead

- Start with a single `:app` module and package-level architecture.
- Use package `com.kezlab.imagecalendar`.
- Use Compose theme tokens from the design system.
- Use Room with `PhotoEntryEntity` first; do not create a `DayRecord` table.
- Use Android Photo Picker first; Camera can be a later subtask.
- Use internal storage copy, not persistent external URI references.

## Local Data/Privacy Lead

- Use Room with separate `PhotoEntry` and `LocalAsset` metadata.
- Store only relative asset paths in the database.
- Save images under app-owned storage and do not modify gallery originals.
- Generate thumbnails at save time.
- Plan backup as `.imagecalendar-backup.zip` with manifest, entries, assets, and checksums, but keep restore disabled for MVP.
- Full app deletion is outside app control; Settings copy must explain device-local storage clearly.

## QA/Delivery Lead

- First CEO cycle should be the `001 + 002` vertical slice.
- The acceptance gate is: no login, Calendar visible, local badge visible, gallery photo saved, Day Detail shows record, Calendar marker updates, no `INTERNET` permission.
- Keep Camera out of first slice to reduce test scope.
- Test no-upload/no-social requirements through UI copy and manifest/static checks.
