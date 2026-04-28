---
type: engineering
status: draft
owner: llm
updated: 2026-04-27
sources:
  - raw/sources/0002-claude-design-prompt.md
---

# Data Model

## Candidate Entities

## DayRecord

- id
- localDate
- entryCount
- coverThumbnailPath
- createdAt
- updatedAt

## PhotoEntry

- id
- dayRecordId
- localDate
- imagePath
- thumbnailPath
- note
- emotionTagId
- capturedAt
- createdAt
- updatedAt

## EmotionTag

- id
- label
- iconName
- colorToken
- sortOrder
- isCustom

## LocalAsset

- id
- entryId
- originalPath
- thumbnailPath
- mimeType
- width
- height
- fileSize
- createdAt

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

- 사진 원본은 앱 내부 저장소에 복사할 것인가?
- DayRecord를 별도 테이블로 둘 것인가, PhotoEntry의 날짜 그룹으로 계산할 것인가?
- EmotionTag는 고정 seed 데이터와 사용자 정의를 함께 허용할 것인가?
- 삭제된 사진의 파일 제거 실패를 어떻게 재시도할 것인가?
- 백업 가져오기 시 ID 충돌을 어떻게 처리할 것인가?
