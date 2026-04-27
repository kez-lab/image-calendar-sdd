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

기술 스택은 아직 확정되지 않았다.

## Fixed Constraints

- 서버 없음
- 로그인 없음
- 네트워크 의존 없는 MVP
- 사진, 메모, 감정 태그는 디바이스 로컬 저장
- 백업은 명시적 파일 내보내기 우선 방식

## Working Assumption

저장소 이름이 `Android-SDD`이므로 구현은 Android-first로 진행할 가능성이 높다. 디자인 레퍼런스는 iPhone 15 Pro 비율과 iOS 스타일을 기준으로 하되, 실제 구현은 Android에서도 자연스럽게 적용 가능한 UI를 목표로 한다.

## Candidate Choices

### Android Native

- Kotlin
- Jetpack Compose
- Gradle
- Room or SQLite for metadata
- App-specific internal storage for copied images and thumbnails
- Android Photo Picker or camera intent

### Cross-Platform Alternative

- Flutter with local SQLite/file storage
- React Native with local SQLite/file storage
- Kotlin Multiplatform if iOS expansion is likely

## Decision Needed

- MVP를 Android native로 구현할지 확정해야 한다.
- 사진 원본을 앱 내부 저장소에 복사할지, 시스템 사진 URI를 참조할지 결정해야 한다.
- 백업 파일 포맷과 가져오기/복원 정책을 결정해야 한다.
