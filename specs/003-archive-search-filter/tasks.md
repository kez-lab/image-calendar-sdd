# Tasks: Archive Search Filter

Status: draft

## Documentation

- [x] Confirm Archive search covers memo and local date only.
- [x] Confirm emotion tags are chip filters, not free-text search.
- [ ] Define supported MVP date query formats.
- [ ] Define Archive navigation target from selected record.
- [ ] Define empty state copy.

## Implementation

- [ ] Create Archive screen using v2 design tokens.
- [ ] Remove placeholder/internal development copy from Archive.
- [ ] Implement recent-month-first grouping.
- [ ] Implement image-centered record grid/list.
- [ ] Implement search bar with placeholder `메모, 날짜로 검색하기`.
- [ ] Implement memo text search.
- [ ] Implement local date label search.
- [ ] Implement fixed emotion tag chip filters.
- [ ] Combine search query and emotion filter with AND semantics.
- [ ] Add no-results empty state.
- [ ] Add first-record empty state CTA when there are no records.
- [ ] Add navigation from Archive record to the appropriate detail context.
- [ ] Add Compose semantics/content descriptions/test tags for search, emotion chips, record cards, empty state CTA, and filters.
- [ ] Ensure Archive contains no social feed/account/upload/share UI.

## Verification

- [x] Test current Archive screen is only placeholder and not MVP-ready. Verified 2026-04-29 with Android CLI screen capture.
- [ ] Test recent-month-first ordering.
- [ ] Test memo search.
- [ ] Test supported date search formats.
- [ ] Test emotion chip filtering.
- [ ] Test combined search plus emotion filter.
- [ ] Test no-result empty state.
- [ ] Test Archive remains local/private in copy and UI.
- [ ] Add Android CLI smoke test for Archive search, emotion filter, no-result state, and record navigation.
