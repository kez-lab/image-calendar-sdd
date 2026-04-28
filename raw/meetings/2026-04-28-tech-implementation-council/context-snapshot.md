# Context Snapshot: Tech Implementation Council

Date: 2026-04-28

## Product Invariants

- No server.
- No login.
- No friend sharing.
- No public feed or social mechanics.
- All records are stored locally on the device.
- User-initiated backup export is the only intended way data leaves the app.

## Active Design/Spec Inputs

- Active design reference: `raw/design/2026-04-28-app-image-calendar-v2/`.
- Active specs:
  - `specs/001-calendar-daily-record/`
  - `specs/002-photo-entry-creation/`
  - `specs/003-archive-search-filter/`
  - `specs/005-settings-data-safety/`

## Previously Locked Decisions

- Archive search covers memo and local date only.
- Emotion tags are explicit filters.
- Calendar local-storage badge is always visible and compact.
- MVP Settings excludes default photo quality and week-start settings.
- First record save navigates to Day Detail.
