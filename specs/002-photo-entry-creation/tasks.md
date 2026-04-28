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
- [ ] Define fixed MVP emotion tag list.
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
- [ ] Implement local `createPhotoEntry` flow with Room persistence.
- [x] Copy selected image into app-specific internal storage.
- [x] Generate thumbnail at save time.
- [ ] Insert Room metadata for `PhotoEntry` and `LocalAsset`.
- [x] Store relative original/thumbnail paths.
- [x] Clean up copied files when save fails before commit.
- [ ] Update local date summary so Calendar marker appears.
- [x] Navigate to saved date Day Detail after successful save.
- [ ] Show save failure state without implying network/server failure.
- [ ] Defer camera capture to a later subtask.

## Verification

- [ ] Test save is blocked without photo.
- [ ] Test save succeeds with photo only.
- [ ] Test save succeeds with photo, memo, and emotion.
- [ ] Test changed date is respected.
- [ ] Test success opens Day Detail and shows the saved card.
- [ ] Test Calendar marker appears after returning from Day Detail.
- [ ] Test Add Record contains no account/upload/sync/share language.
- [ ] Test failed save does not create a partial visible record.
- [x] Test manifest has no `INTERNET` permission.
