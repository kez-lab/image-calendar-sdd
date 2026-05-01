# Tasks: Calendar Daily Record

Status: draft

## Documentation

- [x] Confirm MVP calendar marker design as dot or mini stack.
- [x] Confirm Calendar local-storage badge is always visible and compact.
- [x] Confirm MVP excludes user-configurable week-start setting.
- [x] Confirm `localDate` storage format as `YYYY-MM-DD`.
- [x] Confirm Calendar markers are count/dot based, not image-loading based.
- [ ] Decide practical display behavior for multiple entries per day.

## Implementation

- [x] Create app shell with bottom tab structure: Calendar, Add, Archive, Settings.
- [x] Create Calendar home screen using v2 design tokens.
- [x] Implement month header and month navigation.
- [x] Implement monthly calendar grid using device-local date grouping.
- [x] Add today highlight.
- [x] Add dot/mini-stack record markers for populated dates.
- [x] Add compact always-visible `내 폰에만 저장됨` badge.
- [ ] Add Floating Add Button entry to Add Record.
- [ ] Add empty date state and add-record entry for selected empty dates.
- [x] Add navigation from date selection to Day Record Detail.
- [x] Add Day Detail edit/delete actions for populated dates.
- [x] Replace letter-only bottom navigation icons with distinct accessible icons or labels.
- [x] Add Compose semantics/content descriptions/test tags for month navigation, date cells, local badge, add CTA, and bottom tabs.
- [x] Add Compose semantics/content descriptions/test tags for Day Detail edit/delete actions and dialogs.
- [x] Ensure date cells announce full date, today state, and record count.
- [x] Ensure no account, sync, upload, sharing, or social UI appears on Calendar.

## Verification

- [x] Test app opens calendar without login. Verified 2026-04-29 with official `android run`.
- [x] Test current day highlight. Verified 2026-04-29 screenshot.
- [ ] Test empty date behavior.
- [x] Test record marker rendering. Verified 2026-04-29 after Room fixture save.
- [x] Test date selection navigation. Verified 2026-05-01 by opening `2026-04-29` Day Detail from April calendar.
- [x] Test Day Detail edit action. Verified 2026-05-01 with Android CLI edit dialog capture.
- [x] Test Day Detail delete action and empty state. Verified 2026-05-01 with Android CLI delete confirmation and empty state capture.
- [x] Test Calendar marker removal after delete. Verified 2026-05-01 with `2026-04-29, no records`.
- [x] Test local-storage badge is visible and does not imply upload/sync. Verified 2026-04-29 screenshot and static string scan.
- [x] Test calendar screen contains no server/account/social UI.
- [ ] Test Calendar does not load original images for date markers.
- [ ] Add Android CLI smoke test for Calendar launch, local badge, month navigation, Add entry, Day Detail return, and marker after persistence.
