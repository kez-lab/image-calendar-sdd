---
type: meeting
status: accepted
owner: llm
updated: 2026-04-28
sources:
  - ../../raw/meetings/2026-04-28-mvp-decision-lock/brief.md
  - ../../raw/meetings/2026-04-28-mvp-decision-lock/context-snapshot.md
  - ../../raw/meetings/2026-04-28-mvp-decision-lock/round-1-opinions.md
  - ../../raw/meetings/2026-04-28-mvp-decision-lock/final-notes.md
---

# 2026-04-28 MVP Decision Lock

Date: 2026-04-28  
Topic: MVP decision lock before implementation tasks  
Raw Folder: [raw/meetings/2026-04-28-mvp-decision-lock](../../raw/meetings/2026-04-28-mvp-decision-lock/)

## Context

The project has an accepted local-only product direction and a usable v2 design reference. Four remaining decisions were blocking implementation-task refinement.

## Participants

- Product Lead
- UX Planner
- Local-first Engineering/QA Strategist

## Round 1 Summary

- Product and Engineering both recommended memo/date-only Archive search, with emotion tags handled by explicit filters.
- UX recommended including emotion tags in text search because users may expect visible emotion chips to be searchable.
- All roles agreed the Calendar local-storage badge should remain visible, but compact.
- All roles agreed `Default photo quality` and `Week start day` should be deferred from MVP.
- All roles agreed first record save should navigate to Day Detail.

## Decisions

1. Archive search covers memo and local date only.
2. Emotion tags are filtered through chip filters, not free-text search.
3. Calendar always shows a compact `내 폰에만 저장됨` badge.
4. Settings MVP excludes `Default photo quality` and `Week start day`.
5. First record save navigates to the saved date's Day Detail.

## Rationale

- Search/filter separation keeps MVP behavior easier to explain, implement, and test.
- Persistent local-storage reassurance is part of the product promise, not a temporary onboarding hint.
- Settings options that alter image policy or calendar computation would expand scope before the core loop is proven.
- Day Detail gives immediate confirmation that the first record was saved.

## Action Items

- Update decision log and current state.
- Update design review so these items are no longer open.
- Update Calendar, Add Record, Archive, and Settings specs/tasks.

## Wiki Updates

- [Decision Log](../06-project/decision-log.md)
- [Current State](../00-context/current-state.md)
- [Design Output Review v2](../02-design/design-output-review-2026-04-28-v2.md)
