# Context Snapshot

Date: 2026-04-29

## Product Invariants

- 서버 없음.
- 로그인 없음.
- 친구 공유/SNS 없음.
- 모든 사진, 메모, 감정 태그는 디바이스 로컬에 저장한다.
- 사용자가 외부 업로드나 공유로 오해하지 않도록 UI에서 로컬 저장 원칙을 반복적으로 보여준다.

## Current Implementation State

- Android Native Kotlin + Jetpack Compose project exists.
- Single Activity app shell with bottom tabs: Calendar, Add, Archive, Settings.
- Calendar shows month navigation, today highlight, local-storage badge, and dot markers.
- Add screen supports Android Photo Picker entry, date text input, memo, emotion chips, required-photo validation, and local copy logic.
- Day Detail screen exists after save.
- Archive screen is placeholder-level.
- Settings screen is placeholder-level.
- Records are currently held in Compose in-memory state, not Room.
- `LocalImageStore` copies selected images into app-specific internal storage and generates thumbnails.

## Verified Before Meeting

- `./gradlew assembleDebug` passed.
- Official `android run` installed and launched the debug APK.
- `android layout` and `android screen capture` captured Calendar, Add, Archive, Settings, and Add validation screens.
- Save without photo shows `사진을 먼저 선택해주세요.`
- Static source search found no `INTERNET`, account, upload, sync, cloud, share, login, feed, like, comment, or follow terms in `app/src/main` and Gradle files.

## Known Gaps Entering Meeting

- Room persistence is not connected.
- Full photo selection and save success journey was not verified with a deterministic fixture.
- Archive search/filter is not implemented.
- Settings backup/delete actions are not implemented.
- Onboarding is not implemented.
- Edit/delete for individual records is not implemented.
- Several UI controls lack meaningful accessibility descriptions in Android CLI layout output.
