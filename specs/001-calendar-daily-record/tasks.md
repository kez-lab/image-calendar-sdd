# Tasks: Calendar Daily Record

Status: draft

## Documentation

- [x] Confirm MVP calendar marker design as dot or mini stack.
- [x] Confirm Calendar local-storage badge is always visible and compact.
- [x] Confirm MVP excludes user-configurable week-start setting.
- [ ] Decide practical display behavior for multiple entries per day.
- [ ] Update data model with local date handling.
- [ ] Confirm local thumbnail strategy.

## Implementation

- [ ] Create app shell with bottom tab structure: Calendar, Add, Archive, Settings.
- [ ] Create Calendar home screen using v2 design tokens.
- [ ] Implement month header and month navigation.
- [ ] Implement monthly calendar grid using device-local date grouping.
- [ ] Add today highlight.
- [ ] Add dot/mini-stack record markers for populated dates.
- [ ] Add compact always-visible `내 폰에만 저장됨` badge.
- [ ] Add Floating Add Button entry to Add Record.
- [ ] Add empty date state and add-record entry for selected empty dates.
- [ ] Add navigation from date selection to Day Record Detail.
- [ ] Ensure no account, sync, upload, sharing, or social UI appears on Calendar.

## Verification

- [ ] Test app opens calendar without login.
- [ ] Test current day highlight.
- [ ] Test empty date behavior.
- [ ] Test record marker rendering.
- [ ] Test date selection navigation.
- [ ] Test local-storage badge is visible and does not imply upload/sync.
- [ ] Test calendar screen contains no server/account/social UI.
