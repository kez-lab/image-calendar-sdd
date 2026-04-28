# Plan: Photo Entry Creation

Status: draft

## Technical Approach

Build Add Record as the primary creation flow. The first vertical slice supports gallery selection through Android Photo Picker. The screen should collect a required photo, local date, optional memo, and optional emotion tag, then call the local Entry Repository. On success, navigate to Day Detail for the saved local date.

## Data Impact

Entities involved:

- `PhotoEntry`
- `LocalAsset`
- `EmotionTag`

Required save input:

- localDate
- image source
- optional note
- optional emotionTagId

Storage decisions:

- Copy image into app-specific internal storage.
- Generate thumbnail at save time.
- Store relative original/thumbnail paths.
- Store `localDate` as `YYYY-MM-DD`.
- Roll back generated files when save fails before metadata is committed.

MVP does not model cloud upload, account ownership, shared visibility, or remote sync state.

## UI Impact

Screens involved:

- Add Record
- Day Record Detail
- Calendar Home

Components involved:

- Photo picker entry
- Date picker
- Memo input
- Emotion tag chips
- Save button
- Local storage reassurance copy

## Risks

- Platform photo permissions can make the flow feel heavy if not handled carefully.
- Image storage strategy affects backup size, deletion, and app data retention.
- Save failures must not leave orphaned image files or half-created records.
- Photo Picker source URIs may fail to stream if unavailable; failure copy must be local and non-network copy.

## Dependencies

- Platform decision
- Local DB decision
- Fixed MVP emotion tag list
