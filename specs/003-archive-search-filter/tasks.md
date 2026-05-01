# Tasks: Archive Search Filter

Status: implemented

## Documentation

- [x] Confirm Archive search covers memo and local date only.
- [x] Confirm emotion tags are chip filters, not free-text search.
- [x] Define supported MVP date query formats: `YYYY-MM-DD`, `YYYY-MM`, `YYYY.MM`.
- [x] Define Archive navigation target from selected record: open existing Day Detail for the record's `localDate`.
- [x] Define empty state copy.
- [x] Confirm no external engineering blocker for Archive implementation.
- [x] Confirm fixed MVP emotion tag list before release: `차분`, `기쁨`, `피곤`, `포근`, `바쁨`.

## Implementation

- [x] Create Archive screen using v2 design tokens.
- [x] Remove placeholder/internal development copy from Archive.
- [x] Implement recent-month-first grouping.
- [x] Implement image-centered record grid/list.
- [x] Implement search bar with placeholder `메모, 날짜로 검색하기`.
- [x] Implement memo text search.
- [x] Implement local date label search.
- [x] Implement month query search for `YYYY-MM` and `YYYY.MM`.
- [x] Implement fixed emotion tag chip filters.
- [x] Combine search query and emotion filter with AND semantics.
- [x] Add no-results empty state.
- [x] Add first-record empty state CTA when there are no records.
- [x] Add navigation from Archive record to the appropriate detail context.
- [x] Add clear-filter affordance for no-result state.
- [x] Add Compose semantics/content descriptions/test tags for search, emotion chips, record cards, empty state CTA, and filters.
- [x] Ensure Archive contains no social feed/account/upload/share UI.

## Verification

- [x] Test current Archive screen is only placeholder and not MVP-ready. Verified 2026-04-29 with Android CLI screen capture.
- [x] Test recent-month-first ordering. Verified 2026-05-01 with Android CLI archive initial evidence.
- [x] Test memo search. Verified `MemoSearch` on 2026-05-01 with Android CLI layout evidence.
- [x] Test supported date search formats. Verified `YYYY-MM` on 2026-05-01.
- [x] Test month query search. Verified `2026-04` on 2026-05-01.
- [x] Test emotion chip filtering. Verified `기쁨` on 2026-05-01.
- [x] Test combined search plus emotion filter. Verified no-result state for `2026-04` + `기쁨`.
- [x] Test no-result empty state. Verified 2026-05-01.
- [x] Test Archive remains local/private in copy and UI. Verified by layout and forbidden concept scan.
- [x] Add Android CLI smoke test evidence for Archive search, emotion filter, no-result state, and record navigation.
