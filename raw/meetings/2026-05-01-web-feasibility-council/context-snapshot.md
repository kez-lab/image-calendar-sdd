# Context Snapshot: Web Feasibility Council

Date: 2026-05-01

## Current Product Invariants

- 서버 없음.
- 로그인 없음.
- 친구 공유/SNS 없음.
- 모든 기록 데이터는 현재 디바이스 로컬에 저장한다.
- 사용자가 외부 업로드나 공유로 오해하지 않도록 UI에서 로컬 저장 원칙을 반복적으로 보여준다.

## Current Android State

- Android Native Kotlin + Jetpack Compose.
- Room-backed `PhotoEntry` and `LocalAsset`.
- Internal app-specific image/original/thumbnail storage.
- Backup zip export with `manifest.json`, originals, thumbnails.
- Onboarding, Calendar, Add, Day Detail, Archive, Settings implemented.
- 2026-05-01 Android CLI MVP completion verification passed.
- Public release still needs external design/privacy review.

## Web-Specific Baseline

- A web app must be delivered from a static origin, but record data does not have to be uploaded to an app data server.
- Browser local storage is origin-scoped.
- IndexedDB can store structured local data and blobs.
- OPFS can store origin-private files, but it is browser-managed and deleted with site data.
- Private browsing, storage pressure, browser data clearing, and browser-specific storage policies can cause data loss.

## Known Tensions

- Android says `내 폰에만 저장됨`; web must not use that exact copy.
- Web users may expect account-like cross-device access even when there is no login.
- Web users may also assume a website uploads photos.
- Public web release creates a larger QA matrix than Android-only MVP.
