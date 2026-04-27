---
type: engineering
status: draft
owner: llm
updated: 2026-04-27
sources:
  - raw/sources/0002-claude-design-prompt.md
---

# Coding Conventions

## Status

아직 코드베이스가 없으므로 임시 규칙만 둔다.

## General

- 기능 구현 전 관련 wiki와 spec을 먼저 갱신한다.
- 원격 API, 계정, 동기화 전제를 추가하지 않는다.
- 로컬 저장소 접근은 repository 인터페이스로 분리한다.
- 사진 파일 작업은 메타데이터 DB 업데이트와 일관성을 고려한다.
- 위험 동작은 UI 확인과 도메인 레벨 검증을 모두 둔다.

## Naming

- 사진 추가는 `upload`가 아니라 `add`, `import`, `saveLocal` 계열 용어를 사용한다.
- 데이터 내보내기는 `exportBackup`, 가져오기는 `importBackup`으로 명확히 구분한다.

## Testing

- 로컬 저장, 날짜별 조회, 기록 삭제, 백업/복원, 전체 삭제는 우선 테스트 대상이다.
- UI 테스트는 온보딩, 기록 작성, 캘린더 조회, 아카이브 검색, 설정 데이터 관리 플로우 중심으로 작성한다.
