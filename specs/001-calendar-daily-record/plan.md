# Plan: Calendar Daily Record

Status: draft

## Technical Approach

Create an app shell with bottom navigation and a Calendar home screen. Model records by local date and render a monthly calendar with markers for dates that contain locally stored records. Use a compact, always-visible local-storage badge on the Calendar screen.

## Data Impact

Candidate models:

- `DayRecord`
- `PhotoEntry`
- `LocalAsset`

The calendar should only need a lightweight date summary:

- localDate
- hasRecords
- markerStyle
- recordCount

Date grouping uses the device local date at creation/render time. MVP does not include a user setting for week-start day.

## UI Impact

Screens involved:

- Calendar Home
- Day Record Detail
- Add Record entry point

Components involved:

- Monthly Calendar
- Date Cell Marker
- Floating Action Button
- Local Storage Badge
- Bottom Tab Bar

The local-storage badge is not dismissible in MVP.

## Risks

- Date/timezone handling can put records on the wrong day.
- Date markers can become visually noisy if record counts are overemphasized.
- Calendar rendering can become slow if future thumbnail markers are loaded naively.
- Users may not understand that records are local-only unless copy is clear.
- Week-start behavior can vary by locale if platform defaults are used; MVP should document the chosen implementation once platform is selected.

## Dependencies

- Design system tokens from Claude Design output
- Platform decision: Android native or alternative
- Local storage decision: DB and image file strategy
