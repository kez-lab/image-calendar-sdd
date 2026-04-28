# Tasks: Photo Entry Creation

Status: draft

## Documentation

- [x] Confirm photo is required and memo/emotion are optional.
- [x] Confirm save success navigates to Day Detail.
- [ ] Define fixed MVP emotion tag list.
- [ ] Define photo storage strategy.
- [ ] Define thumbnail generation policy.
- [ ] Define save failure and rollback behavior.

## Implementation

- [ ] Create Add Record screen using v2 design tokens.
- [ ] Add camera capture entry point.
- [ ] Add gallery selection entry point.
- [ ] Add selected photo preview state.
- [ ] Add required-photo validation.
- [ ] Add date selector defaulting to today.
- [ ] Add optional memo input.
- [ ] Add optional fixed emotion tag chips with unselected default state.
- [ ] Add local-only reassurance copy.
- [ ] Implement local `createPhotoEntry` flow.
- [ ] Persist image asset and thumbnail according to chosen storage strategy.
- [ ] Update local date summary so Calendar marker appears.
- [ ] Navigate to saved date Day Detail after successful save.
- [ ] Show save failure state without implying network/server failure.

## Verification

- [ ] Test save is blocked without photo.
- [ ] Test save succeeds with photo only.
- [ ] Test save succeeds with photo, memo, and emotion.
- [ ] Test changed date is respected.
- [ ] Test success opens Day Detail and shows the saved card.
- [ ] Test Calendar marker appears after returning from Day Detail.
- [ ] Test Add Record contains no account/upload/sync/share language.
- [ ] Test failed save does not create a partial visible record.
