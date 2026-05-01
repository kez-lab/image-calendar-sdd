# Plan: Archive Search Filter

Status: draft

## Technical Approach

Build Archive as a local browsing surface over saved `PhotoEntry` records. Query by recent-month grouping, text/date search, and optional emotion filter. Keep search and filter semantics distinct.

For MVP, filter in-memory from the existing repository-backed `PhotoEntry` Flow. This avoids schema churn and is acceptable for the expected early record volume. If local collections become large, move filtering into DAO queries or FTS later.

## Data Impact

Archive query input:

- optional query string for memo/date
- optional emotionTagId filter
- sort order: month descending, then date/created time according to UI design
- supported date query formats: `YYYY-MM-DD`, `YYYY-MM`, `YYYY.MM`

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
- No-result state

## Risks

- Search expectations can expand quickly if emotion tags or OCR are implied.
- Date search can be ambiguous unless supported formats are constrained.
- Large local image sets can make Archive slow without paging/lazy loading.
- Record tap opens Day Detail by date, not a single-record detail; this is simpler but does not anchor to a selected card when multiple records exist on the same day.

## Dependencies

- Local repository query API
- Date formatting policy
- Fixed MVP emotion tag list
- Thumbnail strategy

## Implementation Order

1. Replace placeholder Archive composable with search/filter state.
2. Add query normalization and local filtering helpers.
3. Add month grouping and sorting.
4. Render Archive cards and empty states.
5. Wire card tap to existing Day Detail.
6. Add Android CLI test tags/content descriptions.
7. Verify with official Android CLI and persist raw evidence.
