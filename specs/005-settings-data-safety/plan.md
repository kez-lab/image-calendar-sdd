# Plan: Settings Data Safety

Status: draft

## Technical Approach

Build Settings as the trust and data-safety surface. Keep MVP Settings focused on local storage explanation, backup export, restore status, app information, and dangerous deletion confirmation.

## Data Impact

Settings reads:

- app version
- local storage summary if available
- backup/restore availability state

Settings writes:

- no photo quality setting in MVP
- no week-start setting in MVP
- deletion action can remove records/assets after confirmation
- backup export writes a user-selected local zip package with `manifest.json`, originals, and thumbnails
- full deletion preserves onboarding completion and app preferences

## UI Impact

Screens involved:

- Settings
- Delete All Confirmation

Components involved:

- Local storage trust card
- Backup export button
- Disabled/coming-soon restore row
- Danger zone
- Delete confirmation modal

## Risks

- Users may interpret backup export as cloud backup if copy is vague.
- Full deletion is destructive and must be difficult to trigger accidentally.
- Import/restore copy can create false expectations if it looks enabled.

## Dependencies

- Backup export spec
- Data retention/deletion policy
- Local asset storage strategy
