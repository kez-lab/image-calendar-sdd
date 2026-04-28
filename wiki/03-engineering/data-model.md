---
type: engineering
status: draft
owner: llm
updated: 2026-04-29
sources:
  - raw/sources/0002-claude-design-prompt.md
  - wiki/08-meetings/2026-04-28-tech-implementation-council.md
  - ../../raw/verification/2026-04-29-room-persistence-smoke/README.md
---

# Data Model

## Locked MVP Entities

Implemented Room schema:

- Schema file: `app/schemas/com.kezlab.imagecalendar.core.database.ImageCalendarDatabase/1.json`
- Tables: `photo_entries`, `local_assets`
- Current database version: `1`

## DayRecord

MVP does not create a `DayRecord` table. Calendar summaries are calculated from `PhotoEntry.localDate` grouping.

## PhotoEntry

- id
- localDate
- note
- emotionTagId
- capturedAt
- createdAt
- updatedAt

Storage:

- `localDate` is stored as `YYYY-MM-DD`.
- `createdAt`, `updatedAt`, and `capturedAt` are stored separately from `localDate`.
- Room indexes are required for `localDate`, `createdAt`, and `emotionTagId`.
- Implemented column names use `createdAtMillis`, `updatedAtMillis`, and `capturedAtMillis`.

## EmotionTag

- id
- label
- iconName
- colorToken
- sortOrder
- isCustom

MVP uses fixed seed tags only. Custom tags are deferred.

## LocalAsset

- id
- entryId
- originalRelativePath
- thumbnailRelativePath
- mimeType
- width
- height
- fileSizeBytes
- createdAt

Storage:

- Paths are relative to app-specific internal storage.
- Gallery source URI is not persisted.
- `LocalAsset.entryId` has a foreign key to `PhotoEntry.id`.
- Metadata can cascade when a `PhotoEntry` is removed, but actual file deletion is handled by repository/storage code.
- Implemented fields include `mimeType`, `width`, `height`, and `fileSizeBytes`.

## AppSettings

- id
- onboardingCompleted
- archiveDefaultView
- backupReminderDismissed
- createdAt
- updatedAt

MVP exclusion:

- 기본 사진 품질 설정은 MVP에서 모델링하지 않는다.
- 한 주 시작 요일 설정은 MVP에서 모델링하지 않는다.
- Calendar local-storage badge는 dismiss 상태를 저장하지 않는다.

## BackupManifest

- version
- exportedAt
- entryCount
- assetCount
- appVersion

## Open Modeling Questions

- 삭제된 사진의 파일 제거 실패를 어떻게 재시도할 것인가?
- 백업 가져오기 시 ID 충돌을 어떻게 처리할 것인가?
