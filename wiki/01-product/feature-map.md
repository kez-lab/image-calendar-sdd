---
type: product
status: active
owner: llm
updated: 2026-04-28
sources:
  - raw/sources/0002-claude-design-prompt.md
---

# Feature Map

## Feature Areas

| Area | Purpose | Candidate Spec |
| --- | --- | --- |
| Onboarding | 로컬 전용 개인 기록 앱의 가치를 설명 | part of app shell or first implementation pass |
| Calendar | 날짜별 기록 탐색 | `001-calendar-daily-record` |
| Record Creation | 사진/메모/감정 저장 | `002-photo-entry-creation` |
| Archive | 기록 모아보기, 검색, 감정 필터 | `003-archive-search-filter` |
| Backup/Restore | 백업 파일 내보내기 우선, 가져오기는 후속 검토 | `004-local-backup-restore` |
| Settings | 로컬 저장 안내, 전체 삭제, 앱 정보 | `005-settings-data-safety` |

## Cross-Cutting Requirements

- 서버 업로드나 외부 공유를 암시하는 UI를 만들지 않는다.
- 로컬 저장 안내는 온보딩과 설정에서 명확해야 한다.
- 날짜는 데이터 모델과 UI의 1차 축이다.
- 사진 업로드가 아니라 로컬 추가/저장이라는 용어를 사용한다.
- 삭제, 백업, 복원은 위험도와 결과를 명확히 설명한다.
- MVP의 기록 날짜 표시는 점 또는 미니 스택으로 시작한다.
- 감정 태그는 MVP에서 고정 목록으로 시작한다.

## Recommended SDD Order

1. App shell, navigation, design tokens
2. Calendar daily record
3. Photo entry creation
4. Archive search and filter
5. Local backup export, restore policy later
6. Settings and data safety
