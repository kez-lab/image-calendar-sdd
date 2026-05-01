---
type: privacy
status: active
owner: llm
updated: 2026-05-01
sources:
  - raw/sources/0002-claude-design-prompt.md
  - ../../raw/verification/2026-05-01-mvp-completion/README.md
---

# Data Retention

## Status

MVP 데이터 보존/삭제 정책은 로컬 저장 구조를 기준으로 활성화되었다.

## Initial Principle

사용자가 작성한 개인 기록은 사용자가 삭제할 수 있어야 한다. 삭제된 기록은 캘린더, 상세, 아카이브 검색 결과에서 더 이상 보이면 안 된다.

## Decisions

- 개별 사진 기록 삭제는 Room metadata와 앱 내부 원본 복사본/썸네일을 삭제한다.
- 시스템 사진첩 원본은 앱이 삭제하지 않는다. 앱 내부 복사본만 삭제한다.
- 전체 데이터 삭제는 Room records/assets와 앱 내부 `entries/` 파일들을 삭제한다.
- 전체 데이터 삭제는 온보딩 완료 상태와 앱 preference를 초기화하지 않는다.
- 백업 파일은 사용자가 직접 선택한 외부 위치의 파일로 본다. 앱은 export 이후 보관 상태를 추적하지 않는다.
- 가져오기/복원은 MVP에서 제외하며, 복원 정책 확정 전까지 `준비 중`으로 표시한다.

## Deletion UX

- 개별 기록 삭제는 대상 사진과 메모가 삭제됨을 설명한다.
- 전체 데이터 삭제는 되돌릴 수 없으며 백업이 없으면 복구할 수 없음을 설명한다.
- 삭제 완료 후 사용자가 상태를 확인할 수 있어야 한다.
