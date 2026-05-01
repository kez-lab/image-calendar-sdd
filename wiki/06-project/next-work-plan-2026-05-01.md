---
type: project
status: active
owner: llm
updated: 2026-05-01
sources:
  - ../00-context/current-state.md
  - ../00-context/open-questions.md
  - ../../specs/003-archive-search-filter/spec.md
  - ../02-design/design-output-review-2026-04-28-v2.md
---

# Next Work Plan 2026-05-01

## Executive Summary

Next implementation target: `003 Archive Search Filter`.

Goal:

- Replace the current Archive placeholder with a release-candidate MVP browsing surface.
- Let users find saved local records by recent month, memo/date search, and emotion chip filters.
- Preserve the product invariant: no server, no login, no sharing, no social feed.

Not in this cycle:

- Settings backup/delete-all implementation.
- Onboarding.
- Photo replacement during edit.
- Import/restore.
- Full paging or database FTS.

## Scope Lock

Archive MVP will include:

- Recent-month-first grouping.
- Image-centered record cards.
- Search bar with placeholder `메모, 날짜로 검색하기`.
- Search against memo and local date labels only.
- Fixed emotion chip filters.
- AND semantics between search query and emotion filter.
- Empty state for no saved records.
- No-result state for filters/search with no matches.
- Record card tap opens the existing Day Detail for that record's `localDate`.

Archive MVP will not include:

- Social feed behavior.
- Likes, comments, follows, friends, or public visibility.
- Server search or cloud indexing.
- OCR/image content search.
- Emotion tag free-text search.
- Single-record full-screen detail view.

## Decisions For Open Questions

Supported MVP date query formats:

- `YYYY-MM-DD`, exact local date match.
- `YYYY-MM`, month match.
- `YYYY.MM`, normalized to month match.
- Plain Korean/English month labels are deferred.

Archive navigation target:

- Tapping a record opens the existing Day Detail for that record's date.
- If the date has multiple records, all records for that day are shown.
- Anchoring to the specific selected record is deferred until a dedicated record detail view exists.

Empty state copy:

- No records: `아직 저장된 기록이 없어요` / `사진 한 장으로 하루를 캘린더에 남겨보세요.`
- No results: `검색 결과가 없어요` / `다른 메모, 날짜, 감정 태그로 다시 찾아보세요.`

External manpower:

- No external engineer is required for this Archive slice.
- External designer review is optional after implementation screenshots exist. It is useful for visual fidelity against v2, but not a blocker.
- No legal/privacy specialist is required for Archive because it remains local read-only browsing. Settings backup/delete-all will need stricter privacy/UX review later.

## Implementation Plan

1. Update Archive screen contract.
2. Implement local filtering and grouping over existing repository-backed `PhotoEntry` list.
3. Build Archive UI components: search bar, emotion chips, month headers, image cards, empty states.
4. Add navigation from Archive card to Day Detail.
5. Add Android CLI-friendly semantics/test tags.
6. Verify with deterministic debug fixture records across multiple dates/months.
7. Store raw verification evidence under `raw/verification/2026-05-01-archive-search-filter/`.
8. Update wiki/spec/README and commit/push.

## Technical Notes

- No database schema change is required.
- MVP can filter in Compose from the observed local `entries` Flow because the current expected record count is small.
- If performance becomes visible later, move filtering into DAO queries or add FTS.
- Sorting rule: month descending, then `localDate` descending, then `createdAtMillis` descending.
- Filtering rule: `queryMatch && emotionMatch`.

## TODO

Documentation:

- [x] Lock date query formats.
- [x] Lock Archive navigation target.
- [x] Lock empty/no-result copy.
- [x] Confirm no external blocker for Archive.
- [ ] Update `specs/003-archive-search-filter/spec.md` with the locked decisions.
- [ ] Update `specs/003-archive-search-filter/plan.md` with the implementation approach.
- [ ] Update `specs/003-archive-search-filter/tasks.md` before coding.

Implementation:

- [ ] Replace Archive placeholder copy.
- [ ] Add Archive search text state.
- [ ] Add emotion chip filter state.
- [ ] Implement query normalization for `YYYY-MM-DD`, `YYYY-MM`, and `YYYY.MM`.
- [ ] Group entries by `YearMonth`.
- [ ] Render recent-month-first month sections.
- [ ] Render image-centered record cards with date, optional memo, and optional emotion tag.
- [ ] Add no-record empty state with Add CTA.
- [ ] Add no-result empty state with clear-filter affordance.
- [ ] Wire record tap to existing Day Detail.
- [ ] Add `archive_search`, `archive_emotion_*`, `archive_record_*`, `archive_empty_add`, and `archive_clear_filters` test tags.
- [ ] Keep all Archive copy local/private and avoid upload/sync/share wording.

Verification:

- [ ] Build with `./gradlew assembleDebug`.
- [ ] Install/launch with official `android run`.
- [ ] Create deterministic fixture records across at least two months.
- [ ] Capture Archive initial state with `android layout` and `android screen capture`.
- [ ] Verify recent-month-first grouping.
- [ ] Verify memo search.
- [ ] Verify date search for `YYYY-MM-DD`.
- [ ] Verify month search for `YYYY-MM` or `YYYY.MM`.
- [ ] Verify emotion chip filtering.
- [ ] Verify combined query plus emotion filter.
- [ ] Verify no-result empty state.
- [ ] Verify record tap opens Day Detail.
- [ ] Run forbidden concept scan across app sources.
- [ ] Store all evidence in `raw/verification/2026-05-01-archive-search-filter/`.

Follow-up after Archive:

- [ ] Settings backup export shell, restore coming-soon row, danger zone, delete-all confirmation.
- [ ] Harden deletion cleanup failure handling.
- [ ] Convert manual Android CLI journeys into reusable smoke test scripts or checklists.
