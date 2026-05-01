# Tasks: Settings Data Safety

Status: implemented

## Documentation

- [x] Confirm `Default photo quality` is deferred from MVP.
- [x] Confirm `Week start day` is deferred from MVP.
- [x] Confirm backup export is MVP and import/restore is follow-up.
- [x] Define exact backup export copy.
- [x] Define full data deletion reset scope: local records/assets only; onboarding completion is preserved.
- [x] Define backup file format with `004-local-backup-restore`: MVP v1 zip with `manifest.json`, originals, and thumbnails.

## Implementation

- [x] Create Settings screen using v2 design tokens.
- [x] Add local storage trust card.
- [x] Remove placeholder/internal development copy from Settings.
- [x] Add backup export primary action.
- [x] Add import/restore disabled or coming-soon row.
- [x] Add app information section.
- [x] Add separate danger zone.
- [x] Add full data deletion entry.
- [x] Add full data deletion confirmation modal.
- [x] Implement cancel path with no data mutation.
- [x] Add Compose semantics/content descriptions/test tags for backup export, restore row, danger zone, delete entry, and confirmation modal.
- [x] Ensure no photo quality or week-start setting appears in MVP Settings.
- [x] Ensure no account/login/sync/cloud/share controls appear.

## Verification

- [x] Test local-only explanation is visible. Verified 2026-04-29 with Android CLI screen capture.
- [x] Test current Settings screen is only placeholder and not MVP-ready. Verified 2026-04-29 with Android CLI screen capture.
- [x] Test backup export entry is user-initiated. Verified 2026-05-01 via system file creation picker.
- [x] Test import/restore cannot be mistaken for an enabled MVP action. Verified `준비 중` row on 2026-05-01.
- [x] Test deletion requires confirmation. Verified 2026-05-01.
- [x] Test cancel deletion preserves all data. Verified count remains `2개` after cancel.
- [x] Test confirmed deletion follows defined deletion scope. Verified records become `0개` and Archive empty state appears.
- [x] Test Settings excludes deferred extras. Verified no photo quality or week-start rows in layout evidence.
- [x] Test Settings contains no account/social/cloud controls. Verified by layout and forbidden concept scan.
- [x] Add Android CLI smoke test evidence for Settings local trust copy, backup export action, restore follow-up row, and delete confirmation cancel path.
