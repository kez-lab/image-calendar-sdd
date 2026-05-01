---
type: engineering
status: draft
owner: llm
updated: 2026-05-01
sources:
  - raw/sources/0002-claude-design-prompt.md
  - ../../raw/verification/2026-04-29-room-persistence-smoke/README.md
  - ../../raw/verification/2026-05-01-day-detail-edit-delete/README.md
  - ../../raw/verification/2026-05-01-mvp-completion/README.md
---

# Local Repository Contracts

## Status

원격 API는 없다. 이 문서는 앱 내부 로컬 repository 계약 초안이다.

## Calendar Repository

- `getMonthSummary(yearMonth)`: 월간 날짜별 기록 요약을 반환한다.
- `getDayRecord(localDate)`: 특정 날짜의 기록 목록을 반환한다.

## Entry Repository

- `createPhotoEntryFromUri(uri, input)`: Android Photo Picker URI, 날짜, 메모, 감정 태그를 저장한다.
- `createDebugFixtureEntry(input)`: debug/test 검증용 고정 이미지를 생성하고 저장한다. Release user flow가 아니다.
- `updatePhotoEntry(id, input)`: 기존 기록의 `localDate`, `note`, `emotionTag`를 수정한다. MVP에서는 사진 교체를 포함하지 않는다.
- `deletePhotoEntry(id)`: 기록 metadata를 삭제하고 연결된 로컬 이미지/썸네일 디렉터리 cleanup을 요청한다.
- `exportBackup(destination)`: 사용자가 선택한 로컬 파일 위치에 MVP v1 zip backup을 쓴다.
- `deleteAllUserData()`: Room records/assets와 앱 내부 entry files를 삭제한다.

Create flow contract:

- Input image URI is copied into app-specific internal storage.
- Debug fixture creates a local JPEG directly in app-specific internal storage.
- Thumbnail is generated before metadata commit.
- DB stores relative original/thumbnail paths only.
- `localDate` uses `YYYY-MM-DD`.
- Repository owns the copy, thumbnail, Room insert, and rollback cleanup sequence.
- On copy, thumbnail, or DB failure, partial files are cleaned up before surfacing failure.
- The source gallery URI is not stored.

Update flow contract:

- Only metadata fields are updated in MVP: date, memo, emotion tag.
- `updatedAtMillis` is refreshed on successful update.
- Updated entries are re-read from Room and returned to the UI.
- If `localDate` changes, Day Detail moves to the updated date.

Delete flow contract:

- Repository resolves the linked `LocalAsset` before deleting metadata.
- Room metadata deletion is the user-visible source of truth.
- After metadata deletion, app-specific internal image directory cleanup is invoked.
- Filesystem cleanup failure is surfaced to the caller after metadata deletion.

Backup export contract:

- Destination is a user-selected `Uri` from the Android document creation flow.
- The backup package is a zip file.
- The zip contains `manifest.json`, original image copies, and thumbnails.
- Import/restore is not implemented in MVP.

Full deletion contract:

- Deletes all `photo_entries`, cascaded `local_assets`, and app-managed `entries/` files.
- Preserves onboarding completion and app preferences.
- Surfaces a failure if entry file directory deletion fails.

## Archive Repository

- `searchEntries(query, filters)`: 메모와 로컬 날짜 기반 검색 결과를 반환한다. 감정 태그는 `filters.emotionTagId`로만 처리한다.
- `getArchiveMonths()`: 기록이 있는 월 목록을 반환한다.

## Backup Repository

- `exportBackup(destination)`: 로컬 데이터와 assets를 백업 패키지로 내보낸다.
- `validateBackup(source)`: 가져오기/복원 전 백업 파일을 검증한다.
- `importBackup(source, strategy)`: 백업 패키지를 가져온다. MVP에서는 후속 기능으로 취급한다.

## Contract Principle

계약 이름과 UI 문구에서 `upload`, `sync`, `share`, `public` 같은 서버/공유 암시 표현을 사용하지 않는다.
