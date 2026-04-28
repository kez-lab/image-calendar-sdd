# Plan: Archive Search Filter

Status: draft

## Technical Approach

Build Archive as a local browsing surface over saved `PhotoEntry` records. Query by recent-month grouping, text/date search, and optional emotion filter. Keep search and filter semantics distinct.

## Data Impact

Archive query input:

- optional query string for memo/date
- optional emotionTagId filter
- sort order: month descending, then date/created time according to UI design

Archive query output:

- month groups
- record cards with thumbnail, local date, optional memo, optional emotion tag

## UI Impact

Screens involved:

- Archive
- Day Record Detail

Components involved:

- Search bar
- Emotion tag filter chips
- Month group header
- Image record grid/list
- Empty state

## Risks

- Search expectations can expand quickly if emotion tags or OCR are implied.
- Date search can be ambiguous unless supported formats are constrained.
- Large local image sets can make Archive slow without paging/lazy loading.

## Dependencies

- Local repository query API
- Date formatting policy
- Fixed MVP emotion tag list
- Thumbnail strategy
