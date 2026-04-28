---
type: engineering
status: draft
owner: llm
updated: 2026-04-27
sources:
  - raw/sources/0002-claude-design-prompt.md
  - wiki/08-meetings/2026-04-28-tech-implementation-council.md
---

# Architecture

## Initial Direction

앱은 서버 없는 로컬 전용 구조를 전제로 한다. 캘린더 중심 기록 도메인, 로컬 저장 도메인, 아카이브 탐색 도메인을 분리한다.

## Locked MVP Architecture

- Single Activity Android app.
- Jetpack Compose presentation layer.
- Single `:app` Gradle module for MVP.
- Package-level separation instead of premature multi-module architecture.
- Local repositories mediate Room metadata and internal file storage.
- No remote repository or network layer.

## Package Structure

- `app`: entry point and app-level composition.
- `core.designsystem`: colors, typography, spacing, shared UI primitives.
- `core.model`: UI/domain data models.
- `core.database`: Room entities, DAO, database.
- `core.storage`: internal asset copy, thumbnail generation, rollback cleanup.
- `core.datetime`: local date helpers.
- `feature.calendar`: Calendar home.
- `feature.addrecord`: Add Record.
- `feature.daydetail`: Day Detail.
- `feature.archive`: Archive.
- `feature.settings`: Settings.

## No-Server Rule

- MVP에는 원격 API, 사용자 계정, 클라우드 동기화, 외부 공유 엔드포인트를 만들지 않는다.
- 외부 파일 선택/내보내기는 사용자의 명시적 OS-level action으로만 발생한다.
- Android manifest must not include `INTERNET` permission for MVP.

## Local Save Flow

1. User selects an image through Android Photo Picker.
2. App copies the selected image into app-specific internal storage.
3. App generates a thumbnail in app-specific internal storage.
4. App inserts `PhotoEntry` and `LocalAsset` metadata into Room.
5. If any step before Room insert fails, generated files are removed.
6. If DB insert fails after files are created, generated files are removed.
7. Save success navigates to Day Detail for the saved `localDate`.

## Key Architectural Risks

- 사진 원본 저장 방식과 앱 삭제 시 데이터 손실 안내
- 썸네일 생성과 캘린더 렌더링 성능
- 백업/복원 중 데이터 무결성
- 전체 데이터 삭제의 원자성
- 날짜/타임존 처리

## MVP Strategy

첫 구현은 `001 + 002` 수직 슬라이스로 진행한다. 목업 UI만 만들지 않고, 갤러리 이미지 선택부터 로컬 저장, Day Detail 표시, Calendar marker 반영까지 연결한다.
