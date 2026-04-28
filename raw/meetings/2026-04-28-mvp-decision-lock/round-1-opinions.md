# Round 1 Opinions: MVP Decision Lock

Date: 2026-04-28

## Product Lead

1. Archive search should cover memo and date only. Emotion should remain a separate chip filter.
2. Calendar should always show a compact `내 폰에만 저장됨` badge.
3. `Default photo quality` and `Week start day` should be deferred from MVP.
4. After saving the first record, navigate to Day Detail and show the saved result immediately.

## UX Planner

1. Archive search should ideally include memo, date, and emotion because visible emotion chips may be perceived as searchable.
2. Calendar should always show the local-storage badge, but visually small.
3. `Default photo quality` and `Week start day` should be deferred from MVP.
4. After saving the first record, navigate to Day Detail and show the saved card with a success message.

## Local-first Engineering/QA Strategist

1. Archive search should cover memo and date only. Emotion should remain a separate filter to reduce overlap, data complexity, and QA surface area.
2. Calendar should always show a compact local-storage badge without storing dismiss state.
3. `Default photo quality` and `Week start day` should be deferred because they expand image policy, calendar computation, and test matrix.
4. After saving the first record, navigate to Day Detail so the saved record, date query, and Calendar back-state can be tested together.
