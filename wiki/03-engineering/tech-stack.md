---
type: engineering
status: draft
owner: llm
updated: 2026-04-28
sources:
  - raw/sources/0002-claude-design-prompt.md
---

# Tech Stack

## Current Status

기술 스택은 [Tech Implementation Council](../08-meetings/2026-04-28-tech-implementation-council.md)에서 MVP 기준으로 확정되었다.

## Fixed Constraints

- 서버 없음
- 로그인 없음
- 네트워크 의존 없는 MVP
- 사진, 메모, 감정 태그는 디바이스 로컬 저장
- 백업은 명시적 파일 내보내기 우선 방식

## Locked Stack

- Platform: Android Native
- Language: Kotlin
- UI: Jetpack Compose
- Build: Gradle + Android Gradle Plugin
- Initial module structure: single `:app` module
- Package: `com.kezlab.imagecalendar`
- Local metadata: Room
- Lightweight preferences: DataStore when onboarding/settings persistence is added
- Image input for first slice: Android Photo Picker
- Image storage: app-specific internal storage copy
- Thumbnail strategy: generate thumbnails at save time
- Backup direction: `.imagecalendar-backup.zip` with manifest, entries, assets, and checksums

## First Build Version Targets

- `compileSdk`: 36
- `targetSdk`: 36
- `minSdk`: 26

Rationale:

- The local SDK already has Android 36 installed.
- Android Photo Picker is available without broad media permission on modern devices, with platform support/fallback handled by the Activity Result API.
- `minSdk 26` keeps Java time APIs practical for `LocalDate` handling while covering enough MVP devices.

## Explicit MVP Exclusions

- Cross-platform implementation
- Multi-module architecture
- Camera implementation in the first vertical slice
- Cloud sync
- Network API layer
- `INTERNET` permission
- User-configurable photo quality
- User-configurable week start day

## First Implementation Slice

Implement `001 + 002` as a vertical slice:

- Compose app shell
- Bottom tabs: Calendar, Add, Archive, Settings
- Calendar with local badge, today highlight, month navigation, and markers
- Add Record with gallery selection, date, optional memo, optional emotion tag
- Local image copy and thumbnail generation
- Room metadata insert
- Day Detail confirmation after save
