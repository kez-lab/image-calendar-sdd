# Context Snapshot: MVP Decision Lock

Date: 2026-04-28

## Source State

- Current product direction: local-only personal image calendar.
- Active design reference: `raw/design/2026-04-28-app-image-calendar-v2/`.
- Active design review: `wiki/02-design/design-output-review-2026-04-28-v2.md`.
- Active first spec: `specs/001-calendar-daily-record/`.

## Known Decisions Already Made

- Product promise: `내 폰 안에만 쌓이는 하루 사진 캘린더`.
- First-use flow: onboarding should lead to `첫 기록 만들기`, with `건너뛰고 캘린더 보기`.
- Record creation: photo required, date defaults to today, memo/emotion optional.
- Calendar markers: dot or mini stack for MVP.
- Archive structure: recent-month-first monthly grouping.
- Backup: export-first; import/restore deferred until policy and quality are clearer.

## Remaining Decisions

- Archive search scope.
- Calendar local-storage badge persistence.
- Settings extras MVP inclusion.
- First record post-save destination.
