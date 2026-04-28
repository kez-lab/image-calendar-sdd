---
type: qa
status: draft
owner: llm
updated: 2026-04-28
sources:
  - raw/sources/0002-claude-design-prompt.md
---

# Test Strategy

## Priority Areas

- 로컬 저장 안내
- 기록 작성 30초 플로우
- 캘린더 날짜별 기록 조회
- 아카이브 검색/필터
- 개별 기록 수정/삭제
- 백업 파일 내보내기
- 전체 데이터 삭제

## Test Types

- Unit tests: 날짜 모델, 로컬 repository, 검색/필터, 백업 검증, 삭제 로직
- UI tests: 온보딩, 기록 작성, 캘린더 조회, 아카이브 검색, 설정 데이터 관리
- Manual tests: 디자인/접근성/터치 영역/문구 오해 가능성 확인

## Local Data Regression Tests

- 앱은 로그인 없이 시작된다.
- 기록은 로컬 repository에 저장된다.
- 서버 업로드/동기화 UI가 나타나지 않는다.
- Android manifest에는 `INTERNET` permission이 없다.
- 갤러리 이미지는 source URI를 장기 저장하지 않고 앱 내부 저장소로 복사된다.
- 저장 실패 시 부분 파일과 부분 record가 남지 않는다.
- 기록 삭제 후 캘린더와 아카이브에서 사라진다.
- 전체 삭제 후 기록과 로컬 assets가 제거된다.
- 가져오기/복원이 포함될 경우 백업 파일 검증이 먼저 실행된다.

## First Implementation Gate

- Build succeeds.
- App opens without login.
- Calendar shows the compact local-storage badge.
- Gallery image can be selected and saved locally.
- Save success opens Day Detail for the saved date.
- Calendar marker appears after returning.
- App contains no account, sync, upload, sharing, public feed, likes, comments, or follows.
