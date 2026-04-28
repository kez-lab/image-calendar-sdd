# Final Notes: Weekly App Review

Date: 2026-04-29

## CEO Decision

현재 앱은 “방향성 검증 prototype”으로 분류한다. 출시 가능한 MVP가 되려면 기록이 실제 로컬 저장소에 남고, 앱 재시작 후에도 캘린더/상세/아카이브에서 다시 확인되는 루프가 먼저 닫혀야 한다.

## Locked Decisions

- P0 next slice is `Room persistence + repository + Android CLI QA fixture`.
- `createPhotoEntry` must own image copy, thumbnail generation, Room insert, and cleanup rollback as one repository contract.
- Android CLI smoke test becomes a recurring verification gate for weekly review and release candidate checks.
- Archive and Settings placeholders are release blockers.
- Accessibility semantics, content descriptions, selected states, and stable test tags are part of each feature implementation, not final polish.
- Camera capture, real import/restore, custom emotion tags, cloud/account/social features remain excluded.

## Priority Fixes

### P0

- Implement Room entities, DAO, database, and repository for `PhotoEntry` and `LocalAsset`.
- Replace in-memory entries with repository-backed state.
- Add deterministic debug/test fixture for Android CLI smoke tests.
- Verify photo save success, Day Detail rendering, Calendar marker, and app restart persistence.
- Remove Archive placeholder by implementing search bar, emotion filters, monthly grouping/list, and empty/no-result states.
- Remove Settings placeholder by adding local trust card, backup export action shell, restore follow-up row, danger zone, full deletion entry, and confirmation modal.

### P1

- Add individual record edit/delete flow in Day Detail.
- Replace bottom nav letter icons with distinct icons or unambiguous visual labels.
- Move no-photo validation under the photo section.
- Mark Add screen inputs as required/optional.
- Add date picker or strict date validation.
- Add onboarding after persistence gate.

### P2

- Improve visual polish against v2 design after the functional loops are stable.
- Add richer calendar markers or image thumbnails later if performance allows.

## Open Questions

- Fixed emotion tag list remains the current five tags unless a later design decision changes it.
- MVP edit scope must be decided: memo/emotion/date only, or photo replacement too.
- Full data deletion scope must decide whether onboarding/settings state is included.
- Debug fixture should be debug-only or test-code-only; Engineering recommends debug/test-only and excluded from release behavior.
