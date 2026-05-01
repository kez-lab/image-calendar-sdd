# Tasks: Photo Entry Creation

Status: draft

## Documentation

- [x] Confirm photo is required and memo/emotion are optional.
- [x] Confirm save success navigates to Day Detail.
- [x] Define first vertical slice as gallery-only; Camera is deferred.
- [x] Define photo storage strategy as app-specific internal copy.
- [x] Define metadata DB as Room.
- [x] Define `localDate` storage as `YYYY-MM-DD`.
- [x] Define asset path storage as relative paths.
- [x] Define thumbnail generation at save time.
- [x] Define save failure rollback cleanup requirement.
- [x] Define delete cleanup requirement for app-specific internal image files.
- [x] Define fixed MVP emotion tag list: `차분`, `기쁨`, `피곤`, `포근`, `바쁨`.
- [ ] Define exact thumbnail dimensions and compression format. Current prototype uses 512px square JPEG quality 82.

## Implementation

- [x] Create Add Record screen using v2 design tokens.
- [x] Add Android Photo Picker gallery selection entry point.
- [ ] Add selected photo preview state.
- [x] Add required-photo validation.
- [x] Add date selector defaulting to today.
- [x] Add optional memo input.
- [x] Add optional fixed emotion tag chips with unselected default state.
- [x] Add local-only reassurance copy.
- [x] Implement local `createPhotoEntry` flow with Room persistence.
- [x] Copy selected image into app-specific internal storage.
- [x] Generate thumbnail at save time.
- [x] Insert Room metadata for `PhotoEntry` and `LocalAsset`.
- [x] Store relative original/thumbnail paths.
- [x] Clean up copied files when save fails before commit.
- [x] Update local date summary so Calendar marker appears.
- [x] Navigate to saved date Day Detail after successful save.
- [x] Add repository update flow for existing entry date, memo, and emotion.
- [x] Add repository delete flow for entry metadata and internal image directory cleanup.
- [ ] Show save failure state without implying network/server failure.
- [x] Move no-photo validation message under the photo section as inline error.
- [x] Label inputs as required or optional: photo required, memo optional, emotion optional.
- [x] Add date picker or strict `YYYY-MM-DD` validation before save.
- [x] Add Compose semantics/content descriptions/test tags for photo picker, date input, memo input, emotion chips, validation error, and save button.
- [x] Add deterministic debug/test fixture so Android CLI can verify a successful photo save without manual picker dependency.
- [ ] Defer camera capture to a later subtask.

## Verification

- [x] Test save is blocked without photo. Verified 2026-04-29 with Android CLI screen capture.
- [ ] Test save succeeds with photo only.
- [ ] Test save succeeds with photo, memo, and emotion.
- [ ] Test changed date is respected.
- [x] Test success opens Day Detail and shows the saved card. Verified 2026-04-29 with debug fixture.
- [x] Test Calendar marker appears after returning from Day Detail. Verified 2026-04-29 with Android CLI capture.
- [x] Test Add Record contains no account/upload/sync/share language. Verified 2026-04-29 static string scan.
- [ ] Test failed save does not create a partial visible record.
- [x] Test update persists metadata changes. Verified 2026-05-01 with Android CLI layout after edit.
- [x] Test delete removes visible record. Verified 2026-05-01 with Android CLI empty state after delete.
- [x] Test manifest has no `INTERNET` permission.
- [ ] Add Android CLI smoke test for photo fixture save, Day Detail card, app restart persistence, and Calendar marker.
