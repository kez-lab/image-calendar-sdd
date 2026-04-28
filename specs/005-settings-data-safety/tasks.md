# Tasks: Settings Data Safety

Status: draft

## Documentation

- [x] Confirm `Default photo quality` is deferred from MVP.
- [x] Confirm `Week start day` is deferred from MVP.
- [x] Confirm backup export is MVP and import/restore is follow-up.
- [ ] Define exact backup export copy.
- [ ] Define full data deletion reset scope.
- [ ] Define backup file format with `004-local-backup-restore`.

## Implementation

- [ ] Create Settings screen using v2 design tokens.
- [ ] Add local storage trust card.
- [ ] Add backup export primary action.
- [ ] Add import/restore disabled or coming-soon row.
- [ ] Add app information section.
- [ ] Add separate danger zone.
- [ ] Add full data deletion entry.
- [ ] Add full data deletion confirmation modal.
- [ ] Implement cancel path with no data mutation.
- [ ] Ensure no photo quality or week-start setting appears in MVP Settings.
- [ ] Ensure no account/login/sync/cloud/share controls appear.

## Verification

- [ ] Test local-only explanation is visible.
- [ ] Test backup export entry is user-initiated.
- [ ] Test import/restore cannot be mistaken for an enabled MVP action.
- [ ] Test deletion requires confirmation.
- [ ] Test cancel deletion preserves all data.
- [ ] Test confirmed deletion follows defined deletion scope.
- [ ] Test Settings excludes deferred extras.
- [ ] Test Settings contains no account/social/cloud controls.
